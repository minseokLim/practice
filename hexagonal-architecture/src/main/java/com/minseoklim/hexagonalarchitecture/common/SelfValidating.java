package com.minseoklim.hexagonalarchitecture.common;

import jakarta.validation.*;

import java.util.Set;

public abstract class SelfValidating<T> {
    private final Validator validator;

    protected SelfValidating() {
        try (final ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            this.validator = factory.getValidator();
        }
    }

    @SuppressWarnings("unchecked")
    protected void validateSelf() {
        final Set<ConstraintViolation<T>> violations = this.validator.validate((T) this);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}
