package com.example.infrearnlecturepractice.model.http

import com.example.infrearnlecturepractice.annotation.StringFormatDateTime
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class UserRequest(

        @field:NotNull
        @field:Size(min = 2, max = 10)
        var name:String? = null,

        @field:NotNull
        @field:Min(value = 20)
        var age:Int? = null,

        @field:StringFormatDateTime(pattern = "yyyy-MM-dd HH:mm:ss", message = "날짜 형식이 잘못됨")
        var createAt:String? = null
)