package com.project.project6.validator;

import com.project.project6.command.ChangePwdCommand;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

public class ChangePwdCommandValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ChangePwdCommand.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currentPassword", "required");
        ValidationUtils.rejectIfEmpty(errors, "newPassword", "required");
    }

}
