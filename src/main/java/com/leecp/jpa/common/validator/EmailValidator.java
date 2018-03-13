package com.leecp.jpa.common.validator;


import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;

/**
 * 对邮箱进行验证
 * 可验证是否为空
 * 验证是否合法
 * @author LeeCP
 *
 */
public class EmailValidator extends ValidatorHandler<String> implements Validator<String> {
    private String fieldName;
    //邮箱后缀，格式为 (com|cn|google)$
    private String emailSuffix = "(com|cn)$";

    private String pattern = "^[0-9a-zA-Z]+@[0-9a-zA-Z]+\\." + emailSuffix;

    public EmailValidator(String fieldName) {
        this.fieldName = fieldName;
    }
    /**
     * 邮件验证
     * @param fieldName 字段名称
     * @param userSuffix 只是一个占位标识，false或true都可以
     * @param emailSuffix 邮箱后缀 格式如 String suffix = "(com|cn|google)$";
     */
    public EmailValidator(String fieldName, boolean userSuffix,String emailSuffix) {
        this.fieldName = fieldName;
        this.emailSuffix = emailSuffix;
    }
    /**
     * 邮箱验证
     * @param fieldName 字段名称
     * @param pattern 自定义匹配模式，如 String pattern = "^[0-9a-zA-Z]+@[0-9a-zA-Z]+\\.(com|cn)$";
     */
    public EmailValidator(String fieldName,String pattern) {
        this.fieldName = fieldName;
        this.pattern = pattern;
    }
    @Override
    public boolean validate(ValidatorContext context, String t) {
        if(null == t || "".equalsIgnoreCase(t)) {
            context.addError(ValidationError.create(String.format("%s不能为空！",fieldName))
                    .setErrorCode(-1)
                    .setField(fieldName)
                    .setInvalidValue(t));
            return false;
        }
        if(!t.matches(pattern)) {
            context.addError(ValidationError.create(String.format("%s无效，请输入正确邮箱！",fieldName))
                    .setErrorCode(-1)
                    .setField(fieldName)
                    .setInvalidValue(t));
            return false;
        }
        return true;
    }

}
