package com.example.mvc.annotation

import com.example.mvc.validator.StringFormatDataTimeValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [StringFormatDataTimeValidator::class])
@Target(
        AnnotationTarget.FIELD,
        AnnotationTarget.PROPERTY_GETTER,
        AnnotationTarget.PROPERTY_SETTER,
)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class StringFormatDataTime(
        val pattern: String = "yyyy-MM-dd HH:mm:ss",
        val message: String = "시간 형식이 유효하지 않습니다",
        val groups: Array<KClass<*>> = [], // 이 부분은 아직 안봐도됨, 꼭 들어가는 내용
        val payload: Array<KClass<out Payload>> = [] // 이 부분은 아직 안봐도됨, 꼭 들어가는 내용
        )