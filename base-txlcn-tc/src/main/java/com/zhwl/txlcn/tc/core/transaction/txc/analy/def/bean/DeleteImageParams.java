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
package com.zhwl.txlcn.tc.core.transaction.txc.analy.def.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Description:
 * Date: 2018/12/14
 *
 * @author ujued
 */
@NoArgsConstructor
@Data
public class DeleteImageParams {
    private List<String> primaryKeys;
    private List<String> columns;
    private List<String> tables;
    private String sqlWhere;
}
