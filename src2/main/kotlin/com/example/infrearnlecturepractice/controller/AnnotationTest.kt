package com.example.infrearnlecturepractice.controller

import com.example.infrearnlecturepractice.annotation.StringFormatDateTime
import com.example.infrearnlecturepractice.model.http.UserRequest
import jakarta.validation.Valid
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@Validated
@RequestMapping("/api/annotation-mapping")
class AnnotationTest {

    @PutMapping
    fun getAnnotationMapping(
            @Valid @RequestBody userRequest: UserRequest

    ): UserRequest {
        return userRequest
    }

}