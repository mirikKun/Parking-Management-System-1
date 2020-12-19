package com.kpi.parking.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordConstraintValidator implements ConstraintValidator<Password, String> {

    @Override
    public void initialize(Password constraintAnnotation) {

    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        char[] chars = password.toCharArray();
        for (char ch: chars) {
            if ((ch < '0') || (ch > 'z') || (password.length() < 8) || (password.length() > 255)) {
                return false;
            }
        }
        return true;
    }
}
