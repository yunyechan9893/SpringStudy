package com.example.mvc.tests.model.http

import com.fasterxml.jackson.annotation.JsonProperty

data class UserResponse2(
        var result: Result?=null,
        var description:String?=null,

        @JsonProperty("user")
        var userRequest: MutableList<UserRequest2>?=null
)