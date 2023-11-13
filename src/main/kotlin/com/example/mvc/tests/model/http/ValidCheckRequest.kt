package com.example.mvc.tests.model.http

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Size
import org.jetbrains.annotations.NotNull
import kotlin.math.min

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class ValidCheckRequest(
        @field: NotNull
        @field: Size(min = 2, max = 11)
        var name:String?=null,

        @field:NotNull
        @field:Min(value = 20)
        var age:Int?=null
)