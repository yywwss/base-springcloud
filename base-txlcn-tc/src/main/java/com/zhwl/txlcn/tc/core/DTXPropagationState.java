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
package com.zhwl.txlcn.tc.core;

/**
 * @author lorne
 */
public enum DTXPropagationState {

    /**
     * 创建DTX
     */
    CREATE("starting"),

    /**
     * 加入DTX
     */
    JOIN("running"),

    /**
     * 静默加入DTX
     */
    SILENT_JOIN("default"),

    /**
     * 不参与DTX
     */
    NON("non");


    private String code;


    DTXPropagationState(String code) {
        this.code = code;
    }


    public String getCode() {
        return code;
    }

    public boolean isIgnored() {
        return this.equals(NON);
    }
}
