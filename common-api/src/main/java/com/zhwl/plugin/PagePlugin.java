package com.zhwl.plugin;

import lombok.Data;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.property.PropertyTokenizer;
import org.apache.ibatis.scripting.xmltags.ForEachSqlNode;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandler;
import org.apache.ibatis.type.TypeHandlerRegistry;

import javax.xml.bind.PropertyException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 * 类名称：分页插件
 * 作者： liangzhilin
 * 修改时间：2016年2月1日
 *
 * @version 1.0
 */
//@Component
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
@SuppressWarnings("unchecked")
public class PagePlugin implements Interceptor {

	private static String dialect = "";    //数据库方言
	private static String pageSqlId = ""; //mapper.xml中需要拦截的ID(正则匹配)

	@Override
	public Object intercept(Invocation ivk) throws Throwable {

		if (ivk.getTarget() instanceof RoutingStatementHandler) {
			RoutingStatementHandler statementHandler = (RoutingStatementHandler) ivk.getTarget();
			BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper.getValueByFieldName(statementHandler, "delegate");
			MappedStatement mappedStatement = (MappedStatement) ReflectHelper.getValueByFieldName(delegate, "mappedStatement");

			if (mappedStatement.getId().matches(pageSqlId) ) { //拦截需要分页的SQL
				BoundSql boundSql = delegate.getBoundSql();
				Object parameterObject = boundSql.getParameterObject();//分页SQL<select>中parameterType属性对应的实体参数，即Mapper接口中执行分页方法的参数,该参数不得为空
				Page page = getPageObject(parameterObject);
				Page.MainPageSqlInfo mainPageSqlInfo = getMainPageSqlInfo(page,boundSql.getSql());
				if (parameterObject == null) {
					throw new NullPointerException("parameterObject尚未实例化！");
				} else {
					Connection connection = (Connection) ivk.getArgs()[0];
					String sql = boundSql.getSql();
					//String countSql = "select count(0) from (" + sql+ ") as tmp_count"; //记录统计
					String fhsql = sql;
					String countSql = getCountSql(mainPageSqlInfo); //记录统计 == oracle 加 as 报错(SQL command not properly ended)
					PreparedStatement countStmt = connection.prepareStatement(countSql);
					BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql, boundSql.getParameterMappings(), parameterObject);
					setParameters(countStmt, mappedStatement, countBS, parameterObject,mainPageSqlInfo);
					ResultSet rs = countStmt.executeQuery();
					int count = 0;
					if (rs.next()) {
						count = rs.getInt(1);
					}
					rs.close();
					countStmt.close();
					page.setTotalResult(count);
					String pageSql = generatePageSql(mainPageSqlInfo);
					ReflectHelper.setValueByFieldName(boundSql, "sql", pageSql); //将分页sql语句反射回BoundSql.
				}
			}
		}
		return ivk.proceed();
	}

	private Page.MainPageSqlInfo getMainPageSqlInfo(Page page,String sourceSql) {
		Boolean associativePage = page.getAssociativePage();
		Page.MainPageSqlInfo mainPageSqlInfo = new Page.MainPageSqlInfo();
		mainPageSqlInfo.setPage(page);
		mainPageSqlInfo.setSourceSql(sourceSql);
		if( associativePage != null && associativePage){
			String sourceSqlTemp = sourceSql.toLowerCase();
			Integer selectPosition = sourceSqlTemp.lastIndexOf("select");
			if (selectPosition == -1)throw new RuntimeException("getMainPageSelectFailed");
			mainPageSqlInfo.setMainPageStartPosition(selectPosition);
			int i,j,k=0;
			for(i = selectPosition ,j = sourceSql.length();i<j;i++){
				char c = sourceSql.charAt(i);
				if('(' == c){
					k++;
				}else if (')' == c){
					k--;
					if(k == -1){
						mainPageSqlInfo.setMainPageEndPosition(--i);
						break;
					}
				}
			}
			if (mainPageSqlInfo.getMainPageEndPosition() == null)throw new RuntimeException("getMainPageEndPositionFailed");

			mainPageSqlInfo.setMainPageSql(sourceSql.substring(mainPageSqlInfo.getMainPageStartPosition(),mainPageSqlInfo.getMainPageEndPosition()+1));
		}else{
			mainPageSqlInfo.setMainPageSql(mainPageSqlInfo.getSourceSql());
			mainPageSqlInfo.setMainPageStartPosition(0);
			mainPageSqlInfo.setMainPageEndPosition(mainPageSqlInfo.getSourceSql().length()-1);
		}
		return mainPageSqlInfo;
	}

	/**
	 * 对SQL参数(?)设值,参考org.apache.ibatis.executor.parameter.DefaultParameterHandler
	 *
	 * @param ps
	 * @param mappedStatement
	 * @param boundSql
	 * @param parameterObject
	 * @throws SQLException
	 */
	private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject, Page.MainPageSqlInfo mainPageSqlInfo) throws SQLException {
		ErrorContext.instance().activity("setting parameters").object(mappedStatement.getParameterMap().getId());
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		if (parameterMappings != null) {
			Configuration configuration = mappedStatement.getConfiguration();
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
			paramIndexRange paramIndexRange = getParamIndexRange(mainPageSqlInfo);
			for (int i = paramIndexRange.getStart(),j = paramIndexRange.getEnd(); i <j ; i++) {
				ParameterMapping parameterMapping = parameterMappings.get(i);
				if (parameterMapping.getMode() != ParameterMode.OUT) {
					Object value;
					String propertyName = parameterMapping.getProperty();
					PropertyTokenizer prop = new PropertyTokenizer(propertyName);
					if (parameterObject == null) {
						value = null;
					} else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
						value = parameterObject;
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						value = boundSql.getAdditionalParameter(propertyName);
					} else if (propertyName.startsWith(ForEachSqlNode.ITEM_PREFIX) && boundSql.hasAdditionalParameter(prop.getName())) {
						value = boundSql.getAdditionalParameter(prop.getName());
						if (value != null) {
							value = configuration.newMetaObject(value).getValue(propertyName.substring(prop.getName().length()));
						}
					} else {
						value = metaObject == null ? null : metaObject.getValue(propertyName);
					}
					TypeHandler typeHandler = parameterMapping.getTypeHandler();
					if (typeHandler == null) {
						throw new ExecutorException("There was no TypeHandler found for parameter " + propertyName + " of statement " + mappedStatement.getId());
					}
					typeHandler.setParameter(ps, i + 1, value, parameterMapping.getJdbcType());
				}
			}
		}
	}

	private paramIndexRange getParamIndexRange(Page.MainPageSqlInfo mainPageSqlInfo) {
		String sourceSql = mainPageSqlInfo.getSourceSql();
		paramIndexRange paramIndexRange = new paramIndexRange();
		int count = 0;
		char[] chars = sourceSql.toCharArray();
		Integer start = null;
		Integer end = null;
		for (int i = 0; i<=mainPageSqlInfo.getMainPageEndPosition();i++) {
			if(chars[i] == '?'){
				end = end== null? 0: end+1;
				if(i > mainPageSqlInfo.getMainPageStartPosition()){
					if(start == null)
						start = end;
				}
			}
		}
		if(start == null){
			paramIndexRange.setStart(0);
			paramIndexRange.setEnd(0);
		}else{
			paramIndexRange.setStart(start);
			paramIndexRange.setEnd(end+1);
		}
		return paramIndexRange;
	}

	/**
	 * 根据数据库方言，生成特定的分页sql
	 *
	 */
	private String generatePageSql(Page.MainPageSqlInfo mainPageSqlInfo) {
		Page page = mainPageSqlInfo.getPage();
		String sourceSql = mainPageSqlInfo.getSourceSql();
		if (page != null && notEmpty(dialect)) {
			StringBuffer pageSql = new StringBuffer();
			if ("mysql".equals(dialect)) {
				if(page.getAssociativePage()){
					pageSql.append(sourceSql.substring(0,mainPageSqlInfo.getMainPageEndPosition()+1));
					pageSql.append(" limit " + page.getCurrentResult() + "," + page.getSize());
					pageSql.append(sourceSql.substring(mainPageSqlInfo.getMainPageEndPosition()+1,sourceSql.length()));
				}else{
					pageSql.append(sourceSql);
					pageSql.append(" limit " + page.getCurrentResult() + "," + page.getSize());
				}
			}
			return pageSql.toString();
		} else {
			return sourceSql;
		}
	}

	private String getCountSql(Page.MainPageSqlInfo mainPageSqlInfo){
		String sourceSql = mainPageSqlInfo.getSourceSql();
		Page page = mainPageSqlInfo.getPage();
		if(page!= null && page.getAssociativePage()){
			return "select count(0) from (" + mainPageSqlInfo.getMainPageSql() + ")  tmp_count";
		}else{
			return "select count(0) from (" + sourceSql + ")  tmp_count";
		}
	}

	private Page getPageObject(Object parameterObject) throws NoSuchFieldException {
		Page page = null;
		if (parameterObject instanceof Page) {    //参数就是Page实体
			page = (Page) parameterObject;
		} else if (parameterObject instanceof MapperMethod.ParamMap) {
			MapperMethod.ParamMap paramMap = (MapperMethod.ParamMap) parameterObject;
			for (Object key : paramMap.keySet()) {
				if (paramMap.get(key) instanceof Page) {
					page = (Page) paramMap.get(key);
					break;
				}
			}
		} else {
			throw new NoSuchFieldException(parameterObject.getClass().getName() + "不存在 page 属性！");
		}
		return page;
	}
	/**
	 * 检测字符串是否不为空(null,"","null")
	 *
	 * @param s
	 * @return 不为空则返回true，否则返回false
	 */
	private boolean notEmpty(String s) {
		return s != null && !"".equals(s) && !"null".equals(s);
	}

	/**
	 * 检测字符串是否为空(null,"","null")
	 *
	 * @param s
	 * @return 为空则返回true，不否则返回false
	 */
	private boolean isEmpty(String s) {
		return s == null || "".equals(s) || "null".equals(s);
	}

	@Override
	public Object plugin(Object arg0) {

		return Plugin.wrap(arg0, this);
	}

	@Override
	public void setProperties(Properties p) {
		dialect = p.getProperty("dialect");
		if (isEmpty(dialect)) {
			try {
				throw new PropertyException("dialect property is not found!");
			} catch (PropertyException e) {

				e.printStackTrace();
			}
		}
		pageSqlId = p.getProperty("pageSqlId");
		if (isEmpty(pageSqlId)) {
			try {
				throw new PropertyException("pageSqlId property is not found!");
			} catch (PropertyException e) {

				e.printStackTrace();
			}
		}
	}

	@Data
	private static class paramIndexRange{
		Integer start;
		Integer end;
	}
}
