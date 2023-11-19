package com.example.infrearnlecturepractice.validator

import com.example.infrearnlecturepractice.annotation.StringFormatDateTime
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/*
* 사용자 입력의 유효성을 보다 세부적으로 입력을 검증해야 할 때, 커스텀 어노테이션을 이용하여 예외 검증 로직을 생성할 수 있음
* 이 때, hibernate에서 제공하는 Constraint 어노테이션 을 사용할 수 있음
* */
class StringFormatDataTimeValidator:ConstraintValidator<StringFormatDateTime, String> {
    private var pattern: String? = null

    override fun initialize(constraintAnnotation: StringFormatDateTime?) {
        this.pattern = constraintAnnotation?.pattern
    }

    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return try {
            LocalDateTime.parse(value, DateTimeFormatter.ofPattern(pattern))
            true
        } catch (e:Exception){
            false
        }
    }
}
