package com.example.directive;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/5.
 * 判断用户是否为管理员
 */
public class AdminRoleValidateDirective implements TemplateDirectiveModel {
    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        Subject subject = SecurityUtils.getSubject();
        boolean result = subject.hasRole("admin");
        if(result){
         templateDirectiveBody.render(environment.getOut());
        }
    }
}
