package com.leecp.jpa.common.validator;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;

/**
 * 对字符器进行验证
 * 可验证是否为空
 * 可验证字符串长度，默认是不验证，checkLength=false,min=6,max=18;
 * 错误返回码为-1
 * @author LeeCP
 *
 */
public class StringValidator extends ValidatorHandler<String> implements Validator<String>{
    //验证的字段名称
	private String fieldName;
	//是否验证长度，默认不验证
	private boolean checkLength = false;
	private int min = 6;
	private int max = 18;
	
	public StringValidator(String fieldName) {
		this.fieldName = fieldName;
	}
	
	public StringValidator(String fieldName, boolean checkLength) {
		this.fieldName = fieldName;
		this.checkLength = checkLength;
	}
	
	public StringValidator(String fieldName, boolean checkLength, int min) {
		this.fieldName = fieldName;
		this.checkLength = checkLength;
		this.min = min;
	}
	
	public StringValidator(String fieldName, boolean checkLength, int min, int max) {
		this.fieldName = fieldName;
		this.checkLength = checkLength;
		this.min = min;
		this.max = max;
	}
	@Override
	public boolean validate(ValidatorContext context, String t) {
		if(null == t || "".equalsIgnoreCase(t)) {
			context.addError(ValidationError.create(String.format("%s不能为空！", fieldName))
					.setErrorCode(-1)
					.setField(fieldName)
					.setInvalidValue(t));
			return false;
		}
		if(checkLength) {
			if(t.length() < min || t.length() > max) {
				context.addError(ValidationError.create(String.format("%s长度必须介于%d与%d之间！", fieldName,min,max))
						.setErrorCode(-1)
						.setField(fieldName)
						.setInvalidValue(t));
				return false;
			}
		}
		return true;
	}
	
}

