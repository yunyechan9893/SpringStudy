package com.example.mvc.tests.model.http

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

data class UserRequest2(
        var name:String?=null,
        var age:Int?=null,
        var email:String?=null,
        var phoneNumber: String?=null
)

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
//::class는 왜쓰지
data class Result(
        var resultCode:Int?=null,
        var resultMessage:String?=null
)
