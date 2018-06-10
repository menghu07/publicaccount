package com.solis.quickin.pubaccountproxy.system;

import com.solis.quickin.pubaccountproxy.constant.SystemConstant;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 系统初始化执行，实现配置文件读取和基础配置文件
 * Created by monis on 2018/6/10.
 */
public class IntializerContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("系统初始化开始");
        String sysConfiPath = System.getProperty("sysconfigpath", "/com/solis/sysconfig");
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(sysConfiPath + SystemConstant.SYSTEM_SUB_CONFIG_PATH));
            SystemProperties.appID = properties.getProperty("appid");
            SystemProperties.appsecret = properties.getProperty("appsecret");
            SystemProperties.wechatURL = properties.getProperty("wechaturl");
            System.out.println("properties" + properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("系统初始化结束");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
