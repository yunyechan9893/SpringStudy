package com.example.mvc.tests

import com.example.mvc.tests.model.http.ValidCheckRequest
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody


@Controller
class ValidCheckAndController {
    @PostMapping("/test/main")
    fun postMappingEntitiy(
            @RequestBody
            address:String,

            @Valid
            validCheckRequest: ValidCheckRequest
    ): String {
        address.let {
            ResponseEntity.status(400).body("주소를 입력해주세요")
        }

        return "main.html"
    }

    @GetMapping("/test/main")
    fun getMappingController(): String {
        println("요건 동작은 하냐")
        return "main.html"
    }
}