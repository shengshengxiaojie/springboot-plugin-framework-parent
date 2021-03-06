package com.gitee.starblues.factory.process.pipe.classs.group;

import com.gitee.starblues.annotation.Caller;
import com.gitee.starblues.factory.process.pipe.classs.PluginClassGroup;
import com.gitee.starblues.utils.AnnotationsUtils;


/**
 * 分组存在注解: @Caller
 *
 * @author zhangzhuo
 * @version 2.1.0
 */
public class CallerGroup implements PluginClassGroup {


    /**
     * 自定义 @Caller
     */
    public static final String CALLER = "caller";


    @Override
    public String groupId() {
        return CALLER;
    }

    @Override
    public boolean filter(Class<?> aClass) {
        return AnnotationsUtils.haveAnnotations(aClass, false, Caller.class);
    }
}
