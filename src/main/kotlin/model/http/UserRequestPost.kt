package model.http

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

data class UserRequestPost (
        var name:String? = null,
        var age:Int? = null,
        var email:String? = null,
        var address:String? = null,

        @JsonProperty("phone_number")
        var phoneNumber:String?=null
)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UserRequestPost2 (
        var name:String? = null,
        var age:Int? = null,
        var email:String? = null,
        var address:String? = null,
        var phoneNumber:String?=null
)