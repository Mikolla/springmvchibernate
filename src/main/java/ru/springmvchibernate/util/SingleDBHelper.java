package ru.springmvchibernate.util;

import org.hibernate.cfg.Configuration;
import ru.springmvchibernate.model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SingleDBHelper {
    private static SingleDBHelper singleDBHelper;
 public static  int instanceCount;

    private SingleDBHelper() {
        instanceCount++;
        System.out.println(" ---------------- INSTCOUNTER = " + instanceCount);
    }

    public static SingleDBHelper getInstance() {
        if (singleDBHelper == null) {
            singleDBHelper = new SingleDBHelper();
        }
       return singleDBHelper;
    }


    public  Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.dialect", PropertiesReader.getProperties("dialect"));
        configuration.setProperty("hibernate.connection.driver_class", PropertiesReader.getProperties("driver.class"));
        configuration.setProperty("hibernate.connection.url", PropertiesReader.getProperties("connection.url"));
        configuration.setProperty("hibernate.connection.username", PropertiesReader.getProperties("username"));
        configuration.setProperty("hibernate.connection.password", PropertiesReader.getProperties("password"));
        configuration.setProperty("hibernate.show_sql", PropertiesReader.getProperties("show_sql"));
        configuration.setProperty("hibernate.hbm2ddl.auto", PropertiesReader.getProperties("hbm2ddl.auto"));
        return configuration;
    }
}