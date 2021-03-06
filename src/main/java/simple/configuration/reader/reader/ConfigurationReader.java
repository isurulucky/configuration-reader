/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package simple.configuration.reader.reader;

import simple.configuration.reader.exceptions.ConfigurationReaderException;

public interface ConfigurationReader {

    /**
     * Reads the configuration file at path 'filePath'
     *
     * @param filePath Full path of the configuration file to be read
     * @throws ConfigurationReaderException
     */
    void read (String filePath) throws ConfigurationReaderException;

    /**
     * Returns the configuration value for the key specified by 'key'
     *
     * @param configurationKey  configuration key
     * @return relevant configuration value or null if not found
     * @throws ConfigurationReaderException
     */
    String getConfigurationValue (String configurationKey) throws ConfigurationReaderException;
}
