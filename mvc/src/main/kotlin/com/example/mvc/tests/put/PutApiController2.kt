package com.example.mvc.tests.put

import com.example.mvc.tests.model.http.Result
import com.example.mvc.tests.model.http.UserRequest2
import com.example.mvc.tests.model.http.UserResponse2
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PutApiController2 {
    @PutMapping("/mapping-put2/object")
    fun requestApiObject2(
        @RequestBody UserRequest2: UserRequest2
    ): UserResponse2 {

        return UserResponse2().apply {
            this.result = Result().apply {
                this.resultCode = 200
                this.resultMessage = "성공"
            }
        }.apply {
            this.description = "~~~~~"
        }.apply {
            val userList = mutableListOf<UserRequest2>()

            userList.add(UserRequest2)
            userList.add(UserRequest2().apply {
                this.name = "예찬"
                this.age = 28
                this.email = "dpcks9893@naver.com"
                this.phoneNumber = "01011111111"
            })
            userList.add(UserRequest2().apply {
                this.name = "예찬"
                this.age = 28
                this.email = "dpcks9893@naver.com"
                this.phoneNumber = "01011111111"
            })
        }
    }
}