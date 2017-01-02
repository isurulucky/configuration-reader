# configuration-reader

A wrapper for Apache Commons Configuration library for keeping configuration files in memory and accessing them as needed. 
Currently supports XML and properties file formats, can be extended to support other types as well.

Sample usage:

```java
    final String propertiesFilePath = getClass().getClassLoader().getResource("test.properties").getPath();
    final String xmlFilePath = getClass().getClassLoader().getResource("test.xml").getPath();
    ConfigurationService configurationService = new ConfigurationServiceImpl();
    configurationService.loadConfigurations(new HashSet<String>() {{
      add(propertiesFilePath);
      add(xmlFilePath);  
    }});
    
    configurationService.getConfigurationValue (xmlFilePath, "key1");
    configurationService.getConfigurationValue (propertiesFilePath, "key2");
```

The component also is an osgi bundle, which registers an osgi service to access the Configuration Service for other components. The service interface is **simple.configuration.reader.service.ConfigurationService**, and can be consumed as any other OSGI service.
