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

public class ConfigurationReaderFactory {

    private static final String SUFFIX_XML = ".xml";
    private static final String SUFFIX_PROPERTIES = ".properties";

    public static ConfigurationReader getConfigurationReaderInstance (String filePath) throws ConfigurationReaderException {

        if (filePath.endsWith(SUFFIX_XML)) {
            // XML format config file
            return new XMLConfigurationReader();
        } else if (filePath.endsWith(SUFFIX_PROPERTIES)) {
            // properties format config file
            return new PropertyConfigurationReader();
        } else {
            // unsupported format, error
            throw new ConfigurationReaderException("Unsupported configuration file format for " + filePath);
        }
    }
}
