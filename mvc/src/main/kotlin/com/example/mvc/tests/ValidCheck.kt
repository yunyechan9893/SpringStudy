package com.example.mvc.tests

import com.example.mvc.tests.model.http.ValidCheckRequest
import jakarta.validation.Valid
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test/valid-check")
@Validated
class ValidCheck {

    @GetMapping
    fun getMappingValidCheck(
            @Valid
            validCheckRequest: ValidCheckRequest): ValidCheckRequest {

        return validCheckRequest
    }
}
