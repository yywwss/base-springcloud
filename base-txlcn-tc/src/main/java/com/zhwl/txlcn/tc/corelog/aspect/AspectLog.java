/*
 * Copyright 2017-2019 CodingApi .
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zhwl.txlcn.tc.corelog.aspect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description: 事务日志数据
 * Company: CodingApi
 * Date: 2018/12/19
 *
 * @author codingapi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AspectLog implements Serializable {

    /**
     * id自增主键
     */
    private long id;
    /**
     * groupId hash值
     */
    private long groupIdHash;
    /**
     * unitId hash值
     */
    private long unitIdHash;

    /**
     * 事务单元Id
     */
    private String unitId;
    /**
     * 事务组Id
     */
    private String groupId;
    /**
     * 切面序列化数据
     */
    private byte[] bytes;

    /**
     * 切面方法名称
     */
    private String methodStr;

    /**
     * 保存时间
     */
    private long time;


}
