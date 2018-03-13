package com.leecp.jpa.common.validator;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;

/**
 * 对数字进行验证
 * 可验证字段是否为空
 * 可验证数字长度，默认是不验证，checkLength=false,min=6,max=18;
 * 错误返回码为-1
 * @author LeeCP
 *
 */
public class NumberValidator extends ValidatorHandler<Number> implements Validator<Number>{
    private String fieldName;
	private boolean checkLength = false;
	private int min = 6;
	private int max = 18;
	public NumberValidator(String fieldName) {
		this.fieldName = fieldName;
	}
	public NumberValidator(String fieldName, boolean checkLength) {
		this.fieldName = fieldName;
		this.checkLength = checkLength;
	}
	public NumberValidator(String fieldName, boolean checkLength, int min) {
		this.fieldName = fieldName;
		this.checkLength = checkLength;
		this.min = min;
	}
	public NumberValidator(String fieldName, boolean checkLength, int min, int max) {
		this.fieldName = fieldName;
		this.checkLength = checkLength;
		this.min = min;
		this.max = max;
	}
	@Override
	public boolean validate(ValidatorContext context, Number t) {
		if(null == t) {
			context.addError(ValidationError.create(String.format("%s不能为空！", fieldName))
					.setErrorCode(-1)
					.setField(fieldName)
					.setInvalidValue(t));
			return false;
		}
		if(checkLength) {
			int len = String.valueOf(t).length();
			if(len < min || len > max) {
				context.addError(ValidationError.create(String.format("%s的长度必须介于%d与%d之间", fieldName,min,max))
						.setErrorCode(-1)
						.setField(fieldName)
						.setInvalidValue(t));
				return false;
			}
		}
		return true;
	}
	
	
}

