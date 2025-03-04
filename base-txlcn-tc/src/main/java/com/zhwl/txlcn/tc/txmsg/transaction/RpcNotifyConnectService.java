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
package com.zhwl.txlcn.tc.txmsg.transaction;

import com.zhwl.txlcn.tc.txmsg.RpcExecuteService;
import com.zhwl.txlcn.tc.txmsg.TransactionCmd;
import com.codingapi.txlcn.common.exception.TxClientException;
import com.codingapi.txlcn.txmsg.RpcClientInitializer;
import com.codingapi.txlcn.txmsg.params.NotifyConnectParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.net.InetSocketAddress;

/**
 * Description:
 * Company: CodingApi
 * Date: 2018/12/29
 *
 * @author codingapi
 */
@Service(value = "rpc_notify-connect")
@Slf4j
public class RpcNotifyConnectService implements RpcExecuteService {

    private final RpcClientInitializer rpcClientInitializer;

    @Autowired
    public RpcNotifyConnectService(RpcClientInitializer rpcClientInitializer) {
        this.rpcClientInitializer = rpcClientInitializer;
    }

    @Override
    public Serializable execute(TransactionCmd transactionCmd) throws TxClientException {
        log.info("transactionCmd->{}", transactionCmd);

        NotifyConnectParams notifyConnectParams = transactionCmd.getMsg().loadBean(NotifyConnectParams.class);

        rpcClientInitializer.connect(new InetSocketAddress(notifyConnectParams.getHost(), notifyConnectParams.getPort()));
        return null;
    }
}
