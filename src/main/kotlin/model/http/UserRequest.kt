package model.http

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.PositiveOrZero
import jakarta.validation.constraints.Size

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
        var phoneNumber:String? =null
)