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

package simple.configuration.reader.internal;

import simple.configuration.reader.service.ConfigurationServiceImpl;
import simple.configuration.reader.service.ConfigurationService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.ComponentContext;

/**
 * @scr.component name="configuration.service" immediate="true"
 */
public class ConfigurationReaderComponent {

    private final Log log = LogFactory.getLog(ConfigurationReaderComponent.class);
    private ServiceRegistration serviceRegistration;

    protected void activate (ComponentContext context) throws Exception {

        ConfigurationService configurationService = new ConfigurationServiceImpl();
//        final String mediatorConfigFile = CarbonUtils.getCarbonConfigDirPath() + File.separator + "mediator-conf.properties";
//        final String mobileCountryConfigFile = CarbonUtils.getCarbonConfigDirPath() + File.separator + "MobileCountryConfig.xml";
//        configurationService.loadConfigurations(new HashSet<String>() {{
//            add(mediatorConfigFile);
//            add(mobileCountryConfigFile);
//        }});

        try {
            serviceRegistration = context.getBundleContext().registerService(ConfigurationService.class.getName(), configurationService, null);
        } catch (Throwable t) {
            String errorMsg = "Error in registering ConfigurationService";
            log.error(errorMsg, t);
            throw new Exception(errorMsg, t);
        }
    }

    protected void deactivate (ComponentContext context) throws Exception {

        serviceRegistration.unregister();
    }
}
