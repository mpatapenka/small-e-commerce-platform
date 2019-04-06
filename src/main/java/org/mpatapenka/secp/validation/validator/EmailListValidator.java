package org.mpatapenka.secp.validation.validator;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.mpatapenka.secp.validation.constraint.EmailList;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

@Slf4j
public class EmailListValidator implements ConstraintValidator<EmailList, List<String>> {
    private final EmailValidator hibernateEmailValidator = new EmailValidator();

    @Override
    public boolean isValid(List<String> values, ConstraintValidatorContext context) {
        if (values == null || values.isEmpty()) {
            return true;
        }

        for (String value : values) {
            if (!hibernateEmailValidator.isValid(value, context)) {
                log.warn("Email '{}' is not valid!", value);
                return false;
            }
        }

        return true;
    }
}