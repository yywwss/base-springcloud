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
package com.zhwl.txlcn.tc.support;

import com.zhwl.txlcn.tc.core.DTXLocalContext;

/**
 * Description: 将会在未来版本废弃。推荐使用 {@link DTXUserControls}
 * Date: 19-1-16 下午4:21
 *
 * @author ujued
 * @see DTXUserControls
 */
@Deprecated
public class DTXAspectSupport {

    /**
     * 回滚分布式事务
     */
    public static void setRollbackOnly() {
        DTXUserControls.rollbackCurrentGroup();
    }
}
