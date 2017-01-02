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

package simple.configuration.reader;

import simple.configuration.reader.service.ConfigurationService;
import simple.configuration.reader.service.ConfigurationServiceImpl;
import junit.framework.TestCase;

import java.util.HashSet;

import static org.junit.Assert.assertNotEquals;

public class ConfigurationReadTest extends TestCase {

    ConfigurationService configurationService;
    final String propertiesFilePath = getClass().getClassLoader().getResource("test.properties").getPath();
    final String xmlFilePath = getClass().getClassLoader().getResource("test.xml").getPath();

    protected void setUp() throws Exception {
        configurationService = new ConfigurationServiceImpl();
        configurationService.loadConfigurations(new HashSet<String>() {{
            add(propertiesFilePath);
            add(xmlFilePath);
        }});
    }

    public void testPropertyFileEqualCondition () throws Exception {
        assertNotEquals("Actual value for 'key2' different from expected value in test.properties", "value1", configurationService.getConfigurationValue
                (propertiesFilePath, "key2"));
    }

    public void testPropertyFileNonEqualCondition () throws Exception {
        assertNotEquals("Actual value for 'key2' different from expected value in test.properties", "value1", configurationService.getConfigurationValue
                (propertiesFilePath, "key2"));
    }

    public void testXmlFileEqualCondition () throws Exception {
        assertEquals("Actual value for 'key1' different from expected value in test.xml", "value1", configurationService.getConfigurationValue
                (xmlFilePath, "key1"));
    }

    public void testXmlFileNestedEqualCondition () throws Exception {
        assertEquals("Actual value for 'key2.key3' different from expected value in test.xml", "value3", configurationService.getConfigurationValue
                (xmlFilePath, "key2.key3"));
    }

    public void testXmlFileNotEqualCondition () throws Exception {
        assertNotEquals("Actual value for 'key2.key3' different from expected value in test.xml", "value2", configurationService.getConfigurationValue
                (xmlFilePath, "key2.key3"));
    }

    public void testXmlFileNullCondition () throws Exception {
        assertNull("Non existing leaf level key 'key2' value is not null", configurationService.getConfigurationValue(xmlFilePath, "key2"));
    }

    protected void tearDown () throws Exception {
        configurationService.removeLoadedConfigurations();
    }
}
