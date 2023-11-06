package com.example.mvc.controller.response

import jakarta.validation.Valid
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import model.http.UserRequest

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/response")
@Validated
class ResponseApiController {

    @GetMapping("")
    fun getMapping(
            @RequestParam age:Int?
    ): ResponseEntity<String> {
        return age?.let {
            if (age<20){
                return ResponseEntity.status(400).body("${age}는 20살보다 어립니다")
            }
            return ResponseEntity.ok("OK")
        }.run {
            return ResponseEntity.status(400).body("나이를 입력해주세요")
        }



//        if (age==null){
//            return ResponseEntity.status(400).body("fail")
//        }
//
//        if (age<20){
//            return ResponseEntity.status(400).body("fail")
//        }
//
//        return ResponseEntity.ok("OK")
    }

    @GetMapping("/validation")
    fun getValidatedMapping(
            @NotNull
            @Size(min = 2, max = 5)
            @RequestParam name:String,

            @NotNull(message="age 값을 입력해주세요")
            @Min(value = 20, message = "age는 20보다 커야합니다")
            @RequestParam age:Int
    ): String {
        return "이름은 ${name}, 나이는 ${age}입니다"
    }

    @PostMapping("")
    fun postMapping(@Valid @RequestBody userRequest: UserRequest): ResponseEntity<UserRequest> {
        return ResponseEntity.status(200).body(userRequest)
    }

    @PutMapping("")
    fun putMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<UserRequest> {
        return ResponseEntity.status(HttpStatus.CREATED).body(userRequest)

    }

    @DeleteMapping("/{id}")
    fun deleteMapping(@PathVariable("id") _id:String): ResponseEntity<Any> {
        return ResponseEntity.status(500).body(null)
    }

}

