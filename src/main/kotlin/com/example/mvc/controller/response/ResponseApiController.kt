package com.example.mvc.controller.response

import model.http.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/response")
class ResponseApiController {

    @GetMapping("")
    fun getMapping(@RequestParam age:Int?): ResponseEntity<String> {
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

    @PostMapping("")
    fun postMapping(@RequestBody userRequest: UserRequest): ResponseEntity<UserRequest> {
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

