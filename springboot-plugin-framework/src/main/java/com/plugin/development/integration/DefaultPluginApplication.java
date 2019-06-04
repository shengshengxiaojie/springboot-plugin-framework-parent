package com.plugin.development.integration;

import com.plugin.development.context.AnnotationConfigPluginContextFactory;
import com.plugin.development.context.DefaultPluginContext;
import com.plugin.development.context.PluginContext;
import com.plugin.development.context.PluginContextFactory;
import com.plugin.development.integration.operator.DefaultPluginOperator;
import com.plugin.development.integration.operator.PluginOperator;
import com.plugin.development.integration.user.DefaultPluginUser;
import com.plugin.development.integration.user.PluginUser;
import org.pf4j.PluginManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Objects;

/**
 * @Description: 开发者直接使用的。插件应用
 * @Author: zhangzhuo
 * @Version: 1.0
 * @Create Date Time: 2019-05-28 16:57
 * @Update Date Time:
 * @see
 */
public class DefaultPluginApplication implements ApplicationContextAware, PluginApplication {

    private final Logger log = LoggerFactory.getLogger(DefaultPluginApplication.class);

    private ApplicationContext applicationContext;
    private PluginManager pluginManager;


    private PluginUser pluginUser;
    private PluginOperator pluginOperator;
    private PluginContextFactory pluginContextFactory;

    public DefaultPluginApplication() {
        this(null);
    }


    public DefaultPluginApplication(PluginContextFactory pluginContextFactory) {
        this.pluginContextFactory = pluginContextFactory;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Objects.requireNonNull(applicationContext);
        this.applicationContext = applicationContext;
        this.pluginManager = applicationContext.getBean(PluginManager.class);
        if(pluginContextFactory == null){
            PluginContext pluginContext = new DefaultPluginContext(this.applicationContext);
            this.pluginContextFactory = new AnnotationConfigPluginContextFactory(pluginContext);
        }
        try {
            IntegrationConfiguration bean = applicationContext.getBean(IntegrationConfiguration.class);
            this.pluginUser = new DefaultPluginUser(this.applicationContext, this.pluginManager);
            this.pluginOperator = new DefaultPluginOperator(bean, this.pluginContextFactory, this.pluginManager);
        } catch (Exception e){
            throw new BeanCreationException("Instant PluginUser or PluginOperator Failure : " + e.getMessage(), e);
        }
    }


    @Override
    public PluginOperator getPluginOperator() {
        assertInjected();
        return pluginOperator;
    }

    @Override
    public PluginUser getPluginUser() {
        assertInjected();
        return pluginUser;
    }



    /**
     * 检查注入
     */
    private void assertInjected() {
        if (this.applicationContext == null) {
            throw new RuntimeException("ApplicationContext is null, Please check whether the DefaultPluginApplication is injected");
        }
        if(this.pluginManager == null){
            throw new RuntimeException("PluginManager is null, Please check whether the PluginManager is injected");
        }
        if(this.pluginUser == null){
            throw new RuntimeException("PluginUser is null, " +
                    "Please check whether the PluginManager or ApplicationContext is injected");
        }
        if(this.pluginOperator == null){
            throw new RuntimeException("PluginOperator is null," +
                    " Please check whether the PluginManager or ApplicationContext is injected");
        }
    }
}