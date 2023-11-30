package com.example.mvc.controller.post

import model.http.UserRequestPost
import model.http.UserRequestPost2
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api")
class PostApiController {

    @PostMapping("/post-mapping")
    fun postMapping(): String{
        return "post-mapping"
    }
    @RequestMapping(method =  [RequestMethod.POST], path=["/request-mapping"])
    fun requestMapping():String{
        return "a long ago post"
    }

    @PostMapping("/post-mapping/object")
    fun postRequestObject(@RequestBody userRequestPost: UserRequestPost): UserRequestPost {
        println(userRequestPost)
        return userRequestPost
    }

    @PostMapping("/post-mapping/object2")
    fun postRequestObject2(@RequestBody userRequestPost2: UserRequestPost2): UserRequestPost2 {
        println(userRequestPost2)
        return userRequestPost2
    }
}