package com.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

public class Validator  implements org.springframework.validation.Validator{
	
	private static final Pattern VALID_EMAIL = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$", Pattern.CASE_INSENSITIVE);
	private static final Pattern VALID_PASSWORD = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&*+=])(?=\\S+$).{8,}", Pattern.CASE_INSENSITIVE);
	
	private Validator(){
		
	}
	
	public static boolean isValidString(String str){
		if(str==null || str.isEmpty()){
			return false;
		}
		return true;
	}
	
	public static boolean isValidEmail(String email) {
		if(!isValidString(email)){
			return false;
		}
		Matcher matcher = VALID_EMAIL.matcher(email);
        return matcher.find();
	}
	
	public static boolean isValidPassword(String password){
		if(!isValidString(password)){
			return false;
		}
		Matcher matcher = VALID_PASSWORD.matcher(password);
		return matcher.find();
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "username.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.required");
	}

}
