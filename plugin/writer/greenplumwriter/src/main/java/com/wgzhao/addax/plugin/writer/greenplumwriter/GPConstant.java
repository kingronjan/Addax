/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.wgzhao.addax.plugin.writer.greenplumwriter;

import com.wgzhao.addax.common.base.Constant;

public class GPConstant
        extends Constant
{
    // 字段分隔符
    public static final char DELIMITER = '\u0001';

    // 字段引用符号，这里没有使用常见的双引号，是考虑到json数据类型会包含该符号
    public static final char QUOTE_CHAR = '\u0002';

    public static final char NEWLINE = '\n';

    public static final char ESCAPE = '\\';

    // 因为 GP DB 服务端 对 COPY FROM 的 CSV 格式做了这样的限制，如果单个元组大于4 MB，只能使用 insert
    public static final int MAX_CSV_SIZE = 4194304;
}
