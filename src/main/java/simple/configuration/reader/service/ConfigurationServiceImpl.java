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

import simple.configuration.reader.exceptions.ConfigurationReaderException;
import simple.configuration.reader.reader.ConfigurationReaderFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import simple.configuration.reader.exceptions.ConfigurationServiceException;
import simple.configuration.reader.reader.ConfigurationReader;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ConfigurationServiceImpl implements ConfigurationService {

    private final Log log = LogFactory.getLog(ConfigurationServiceImpl.class);

    // map of config file path -> ConfigurationReader instance
    private Map<String, ConfigurationReader> configurationReaders;

    public ConfigurationServiceImpl () {
        configurationReaders = new HashMap<String, ConfigurationReader>();
    }

    public void loadConfigurations(Set<String> filePaths) throws ConfigurationServiceException {

        if (filePaths == null || filePaths.isEmpty()) {
            log.error("File paths are invalid; not configuration will be read");
            return;
        }

        for (String filePath : filePaths) {
            ConfigurationReader configurationReader;
            // get ConfigurationReader instance
            try {
                configurationReader = ConfigurationReaderFactory.getConfigurationReaderInstance(filePath);
            } catch (ConfigurationReaderException e) {
                throw new ConfigurationServiceException(e);
            }
            // read and load the configuration
            try {
                configurationReader.read(filePath);
            } catch (ConfigurationReaderException e) {
                throw new ConfigurationServiceException(e);
            }
            // store the ConfigurationReader instance in the map, against file path
            configurationReaders.put(filePath, configurationReader);
        }
    }

    public String getConfigurationValue(final String filePath, String configurationKey) throws ConfigurationServiceException {

        // get stored ConfigurationReader instance
        ConfigurationReader configurationReader = configurationReaders.get(filePath);
        // if not found, try re-loading it from the file system. This should not happen under normal circumstances
        if (configurationReader == null) {
            synchronized (this) {
                configurationReader = configurationReaders.get(filePath);
                if (configurationReader == null) {
                    log.info("Configuration Reader not found for file path " + filePath + ", will try to re-load from the file system");
                    loadConfigurations(new HashSet<String>() {{
                        add(filePath);
                    }});
                    configurationReader = configurationReaders.get(filePath);
                }
            }
        }

        try {
            return configurationReader.getConfigurationValue(configurationKey);
        } catch (ConfigurationReaderException e) {
            throw new ConfigurationServiceException(e);
        }
    }

    public void removeLoadedConfigurations() throws ConfigurationServiceException {
        configurationReaders.clear();
    }


}
