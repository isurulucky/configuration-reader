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

package simple.configuration.reader.service;

import simple.configuration.reader.exceptions.ConfigurationServiceException;

import java.util.Set;

public interface ConfigurationService {

    /**
     * Loads the configuration to the memory to be used afterwards
     *
     * @param filePaths  Set of paths corresponding to a set of configuration files to be read
     * @throws ConfigurationServiceException
     */
    void loadConfigurations (Set<String> filePaths) throws ConfigurationServiceException;

    /**
     * Finds and returns the relevant configuration value from the file specified by path 'filePath'
     * and the configuration key 'configurationKey'
     *
     * @param filePath path for the configuration file
     * @param configurationKey configuration key for the relevant configuration element
     * @return configuration value or null if not found
     * @throws ConfigurationServiceException
     */
    String getConfigurationValue (String filePath, String configurationKey) throws ConfigurationServiceException;

    /**
     * Clears the loaded configuration
     *
     * @throws ConfigurationServiceException
     */
    void removeLoadedConfigurations () throws ConfigurationServiceException;
}
