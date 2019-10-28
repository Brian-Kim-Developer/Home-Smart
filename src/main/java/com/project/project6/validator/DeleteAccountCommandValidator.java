package com.project.project6.validator;

import com.project.project6.command.DeleteAccountCommand;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;

public class DeleteAccountCommandValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return DeleteAccountCommand.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required");
    }

}
