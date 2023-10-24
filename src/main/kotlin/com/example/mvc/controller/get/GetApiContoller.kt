package com.example.mvc.controller.get

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController // REST API Contraller 동작
@RequestMapping("/api") // http://localhost:8080/api
class GetApiContoller {
    @GetMapping("/hello") //GET http://localhost:8080/api/hello
    fun hello(): String{
        return "Hello world"
    }
//    옛날 방식이라 jdk 최신버전에서는 사용할 수 없음
//    @RequestMapping(method = (RequestMethod.GET), path = ["request-mapping"])
//    fun requestMapping(){
//      return "request-mapping"
//    }

    // path=[] 사용시 여러 주소 지정 가능
    @GetMapping(path = ["/hello1", "hello2", "/hello3"]) //GET http://localhost:8080/api/hello
    fun hello2(): String{
        return "Hello world"
    }

    // Header값 받기
    @GetMapping("/get-mapping/path-variable/{name}") //GET http://localhost:8080/api/hello
    fun pathVariable(@PathVariable name:String): String{
        return "나의 이름은 ${name}입니다"
    }
    
}