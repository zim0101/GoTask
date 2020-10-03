package com.go_task.config;

import com.go_task.utils.enums.EnvironmentType;

public class ApplicationConfig {

    /**
     * Application environment type.
     */
    public static EnvironmentType environment = EnvironmentType.LOCAL;

    // hibernate config paths according to production types.
    private static final String localDBConfigXML = "hibernate.cfg.xml";
    private static final String testDBConfigXML = "hibernate-test.cfg.xml";
    private static final String productionDBConfigXML = "hibernate-production" +
            ".cfg.xml";
    private static final String stagingDBConfigXML = "hibernate-staging" +
            ".cfg.xml";


    /**
     * @return hibernate configuration xml according to environment type.
     */
    public static String getHibernateXMLPath() {
        return switch (environment) {
            case TEST -> testDBConfigXML;
            case LOCAL -> localDBConfigXML;
            case PRODUCTION -> productionDBConfigXML;
            case STAGING -> stagingDBConfigXML;
        };
    }
}


