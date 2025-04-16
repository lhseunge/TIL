추후 지원해줄지 모르지만 24/10/30 기준 `@Valid` 는 enum 값의 유효성 검사를 해주지 않는다. 

이런 케이스에 커스텀 어노테이션을 만들어서 enum 클래스의 유효성 검사를 진행하기도 한다. 

Enum 값의 유효성 검사를 진행하는 클래스이다.

```java
public class EnumValidator implements ConstraintValidator<EnumValid, String> {
    private Enum<?>[] enumValues;

    @Override
    public void initialize(EnumValid constraintAnnotation) {
        enumValues = constraintAnnotation.enumClass().getEnumConstants();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // 필수 값이 아니면 null 허용
        }
        return Arrays.stream(enumValues)
                .anyMatch(enumValue -> enumValue.name().equals(value));
    }
}
```

검사할 필드에 적용하기 위한 annotation이다. 

```java
@Constraint(validatedBy = EnumValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface EnumValid {
    Class<? extends Enum<?>> enumClass();
    String message() default "Invalid value. This is not permitted.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
```

적용 예제 

```java
public class Test {

    @NotEmpty
    @EnumValid(enumClass = AuthTypeEnum.class, message = "authType must be one of: sms, email")
    private String authType;
}

public enum AuthTypeEnum {
    sms,
    email
}
```

