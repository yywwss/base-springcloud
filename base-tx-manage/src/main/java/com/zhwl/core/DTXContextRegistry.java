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
package com.zhwl.core;

import com.codingapi.txlcn.common.exception.TransactionException;

/**
 * Description: 事务组生命期管理
 * Date: 19-1-21 下午2:51
 *
 * @author ujued
 */
public interface DTXContextRegistry {

    /**
     * 创建事务组上下文
     *
     * @param groupId groupId
     * @return DTXContext
     * @throws TransactionException TransactionException
     */
    DTXContext create(String groupId) throws TransactionException;

    /**
     * 获取事务组上下文
     *
     * @param groupId groupId
     * @return DTXContext
     * @throws TransactionException TransactionException
     */
    DTXContext get(String groupId) throws TransactionException;

    /**
     * 销毁上下文
     *
     * @param groupId groupId
     */
    void destroyContext(String groupId);

    /**
     * 事务状态
     *
     * @param groupId groupId
     * @return state
     */
    int transactionState(String groupId);
}
