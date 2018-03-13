package com.leecp.jpa.common.validator;

import com.baidu.unbiz.fluentvalidator.ValidationError;
import com.baidu.unbiz.fluentvalidator.Validator;
import com.baidu.unbiz.fluentvalidator.ValidatorContext;
import com.baidu.unbiz.fluentvalidator.ValidatorHandler;
import com.leecp.jpa.common.base.ResultConstant;
import com.sun.tools.javac.main.Main;

/**
 * 数据长度验证
 * @author LeeCP
 *
 */
public class LengValidator extends ValidatorHandler<String> implements Validator<String>{

    private int min;
	private int max;
	private String fieldName;
	
	public LengValidator(int min , int max , String fieldName) {
		this.min = min;
		this.max = max;
		this.fieldName = fieldName;
	}

	@Override
	public boolean validate(ValidatorContext context, String data) {
		if(null == data || data.length() < min || data.length() > max ){
			context.addError(ValidationError.create(String.format("%s数据的长度必须介于%d-%d之间", fieldName,min,max))
					.setErrorCode(ResultConstant.DATA_LENGTH_INVALID.getCode())
					.setErrorMsg(ResultConstant.DATA_LENGTH_INVALID.getMessage())
					.setField(fieldName));
			return false;
		}
		return true;
	}
	
}
