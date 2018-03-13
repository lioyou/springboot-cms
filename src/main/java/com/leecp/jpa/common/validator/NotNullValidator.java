package com.leecp.jpa.common.validator;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.leecp.jpa.common.base.ResultConstant;

public class NotNullValidator extends ValidatorHandler<String> implements Validator<String>{
    private String fieldName;
	
	public NotNullValidator( String fieldName) {
		this.fieldName = fieldName;
	}

	@Override
	public boolean validate(ValidatorContext context, String t) {
		if(null == t || "".equals(t)) {
			context.addError(ValidationError.create(String.format("%s 不能为空！",fieldName))
					.setErrorCode(ResultConstant.DATA_EMPTY.getCode())
					.setErrorMsg(ResultConstant.DATA_EMPTY.getMessage())
					.setField(fieldName));
			return false;
		}
		return true;
	}
	
}