package com.example.springtodolist.model.http

import jakarta.validation.Validation
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.validation.FieldError

class TodoDtoTest {

    var validator = Validation.buildDefaultValidatorFactory().validator

    @Test
    fun todoDtoTest(){
        val todoDto = TodoDto().apply {
            this.title="테스트"
            this.description="테스트 중"
            this.schedule="2023-11-23 11:25:00"
        }

        println(todoDto)

        val result = validator.validate(todoDto)

        result.forEach{
            println(it.propertyPath.last().name)
            println(it.message)
            println(it.invalidValue)
        }

        Assertions.assertEquals(true, result.isEmpty())
    }
}