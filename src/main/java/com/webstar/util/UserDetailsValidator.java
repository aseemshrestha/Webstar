package com.webstar.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.webstar.models.UserDetails;
@Component
public class UserDetailsValidator implements Validator
{

    @Override
    public boolean supports(Class<?> aClass)
    {
        return UserDetails.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors)
    {
        UserDetails user = (UserDetails)o;

        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Password mismatch");
        }

        if (!Utils.emailValidator(user.getEmail())) {
            errors.rejectValue("email", "Invalid Email");
        }

        if (!Utils.validatePhoneNumber(user.getPhone())) {
            errors.rejectValue("phone", "Invalid Phone");
        }
    }

}
