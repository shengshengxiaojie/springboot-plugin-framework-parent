package com.basic.example.main.config;

import com.gitee.starblues.integration.PluginApplication;
import com.gitee.starblues.integration.listener.PluginListener;
import com.gitee.starblues.integration.user.PluginUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


/**
 * 插件监听者
 *
 * @author zhangzhuo
 * @version 1.0
 */

public class ExamplePluginListener implements PluginListener {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final PluginUser pluginUser;

    public ExamplePluginListener(PluginApplication pluginApplication){
        this.pluginUser = pluginApplication.getPluginUser();
    }


    @Override
    public void registry(String pluginId) {
        logger.info("Listener: registry pluginId {}", pluginId);
    }

    @Override
    public void unRegistry(String pluginId) {
        logger.info("Listener: unRegistry pluginId {}", pluginId);
    }

    @Override
    public void failure(String pluginId, Throwable throwable) {

    }
}