/*
 *
 *  * Copyright 2020 http://www.hswebframework.org
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package org.hswebframework.web.id;

import org.hswebframework.utils.RandomUtil;
import org.hswebframework.web.utils.DigestUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * ID生成器,用于生成ID
 *
 * @author zhouhao
 * @since 3.0
 */
@FunctionalInterface
public interface IDGenerator<T> {
    T generate();

    /**
     * 空ID生成器
     */
    IDGenerator<?> NULL = () -> null;

    @SuppressWarnings("unchecked")
    static <T> IDGenerator<T> getNullGenerator() {
        return (IDGenerator<T>) NULL;
    }

    /**
     * 使用UUID生成id
     */
    IDGenerator<String> UUID = () -> java.util.UUID.randomUUID().toString();

    /**
     * 随机字符
     */
    IDGenerator<String> RANDOM = RandomUtil::randomChar;

    /**
     * md5(uuid())
     */
    IDGenerator<String> MD5 = () -> DigestUtils.md5Hex(UUID.generate());

    /**
     * 雪花算法
     */
    IDGenerator<Long> SNOW_FLAKE = SnowflakeIdGenerator.getInstance()::nextId;

    /**
     * 雪花算法转String
     */
    IDGenerator<String> SNOW_FLAKE_STRING = () -> String.valueOf(SNOW_FLAKE.generate());

    /**
     * 雪花算法的16进制
     */
    IDGenerator<String> SNOW_FLAKE_HEX = () -> Long.toHexString(SNOW_FLAKE.generate());
}
