package com.dwarfeng.toolhouse.sdk.util;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 权限等级字段有效性验证注解。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Documented
@Constraint(
        validatedBy = {ValidPermissionLevel.InternalConstraintValidator.class}
)
@Target({
        ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR,
        ElementType.PARAMETER, ElementType.TYPE_USE
})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPermissionLevel {

    String message() default "invalid data type";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class InternalConstraintValidator implements ConstraintValidator<ValidPermissionLevel, Integer> {

        // 执行校验操作
        @Override
        public boolean isValid(Integer value, ConstraintValidatorContext context) {
            try {
                return Constants.permissionLevelSpace().contains(value);
            } catch (Exception e) {
                return false;
            }
        }
    }
}
