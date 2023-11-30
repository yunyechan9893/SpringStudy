package model.http

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

class UserResponse (
        var result: Result?=null,
        var description:String?=null,

        @JsonProperty("user")
        var userRequest: MutableList<UserRequest>?=null
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class Result(
        var resultCode:String?=null, //result_code
        var resultMessage:String?=null //result_message
)