package com.solis.quickin.pubaccountproxy.constant;

import java.io.File;

/**
 * Created by monis on 2018/6/10.
 */
public interface SystemConstant {

    /**
     * 模块名称
     */
    String MODULE_NAME = "pubaccountproxy";

    /**
     * 系统配置文件子路径
     */
    String SYSTEM_SUB_CONFIG_PATH = File.separator + MODULE_NAME + File.separator + "sysconfig" + File.separator + "sys.properties";

    /**
     * 公众号调用相关常量
     */
    /**
     * appid 参数名称
     */
    String APPID = "appid";

    /**
     * appsecret 参数名称
     */
    String APPSECRET = "secret";

    /**
     * grant_type 参数名称
     */
    String GRANT_TYPE = "grant_type";
}
