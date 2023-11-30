package com.example.infrearnlecturepractice.annotation


import com.example.infrearnlecturepractice.validator.StringFormatDataTimeValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [StringFormatDataTimeValidator::class])
@Target( // 이 어논테이션을 어떤 타입에 사용할 수 있는지 지정
        AnnotationTarget.FIELD, // 필드에 적용할 수 있음
        AnnotationTarget.PROPERTY_GETTER, // Getter에 적용할 수 있음
        AnnotationTarget.PROPERTY_SETTER // Setter에 적용할 수 있음
)
@Retention(AnnotationRetention.RUNTIME) // 어노테이션의 라이프사이클을 담당
//RetentionPolicy.SOURCE : 소스 코드(.java)까지 남아있는다.
//RetentionPolicy.CLASS : 클래스 파일(.class)까지 남아있는다.(=바이트 코드)
//RetentionPolicy.RUNTIME : 런타임까지 남아있는다.(=사실상 안 사라진다.)
@MustBeDocumented // API의 일부분으로 문서화하기 위해 사용.
annotation class StringFormatDateTime(
        val pattern:String = "yyyy-MM-dd HH:mm:ss",
        val message:String = "시간 형식이 유효하지 않습니다",
        val groups  :Array<KClass<*>> = [],
        val payload: Array<KClass<out Payload>> = []
)
