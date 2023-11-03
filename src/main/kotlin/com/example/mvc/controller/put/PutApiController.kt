package com.example.mvc.controller.put

import model.http.Result
import model.http.UserRequest
import model.http.UserResponse
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PutApiController {
    @PutMapping("put-mapping")
    fun putMapping(): String{
        return "put-mapping"
    }

    @RequestMapping(method = [RequestMethod.PUT], path = ["/request-mapping"])
    fun requestMapping():String{
        return "request-mapping - put method"
    }
    @PutMapping(path = ["/put-mapping/object"])
    fun putMappingObject(@RequestBody userRequest: UserRequest): UserResponse {
        // 0. Response
        return UserResponse().apply {
            this.result = Result().apply {
                this.resultCode = "OK"
                this.resultMessage = "성공"
            }
        }.apply{
            // 2. description
            this.description = "~~~~~~~~"
        }.apply {
            // 3. user, mutable list
            val userList = mutableListOf<UserRequest>()

            userList.add(userRequest)
            userList.add(UserRequest().apply {
                this.name="a"
                this.age = 10
                this.email = "a@gmail.com"
                this.address = "b address"
                this.phoneNumber = "010-1111-1111"
            })
            userList.add(UserRequest().apply {
                this.name="b"
                this.age = 11
                this.email = "b@gmail.com"
                this.address = "b address"
                this.phoneNumber = "010-2222-1111"
            })

            this.userRequest = userList
        }
    }
}