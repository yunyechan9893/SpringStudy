package model.http

import com.example.mvc.annotation.StringFormatDataTime
import jakarta.validation.constraints.AssertTrue
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.PositiveOrZero
import jakarta.validation.constraints.Size
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class UserRequest (

        @field:NotEmpty
        @field:Size(min=2, max=5)
        var name:String? = null,

        @field:PositiveOrZero
        var age:Int? = null,

        @field:Email
        var email:String? = null,

        @field:NotBlank
        var address:String? = null,

        @field:Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}\$")
        var phoneNumber:String? =null,

        @field:StringFormatDataTime(pattern="yyyy-MM-dd HH:mm:ss", message = "패턴이 올바르지 않습니다")
        var createdAt:String?=null //yyyy-MM-dd HH,mm,ss ex) 2023-11-07 13:00:00

){

}