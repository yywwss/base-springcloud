package com.zhwl.bean;

import lombok.Data;
import java.util.Date;

/**
 * 为了记录当前登录用户。由于采用分布式，当前登录用户信息只保留在网关项目内存中
 * 其他服务项目要获取当前登录用户信息，只能通过调用网关获取并返回SysUser对象。
 */
@Data
public class SysUser {
    private String id;
    private String name; //姓名
    private String phone; //手机号码
    private String telephone; //住宅电话
    private String address;  //联系地址
    public String username;  //用户名
    private String password;  //密码
    private String userface;  //用户图像
    private Date addTime;  //添加时间




}
