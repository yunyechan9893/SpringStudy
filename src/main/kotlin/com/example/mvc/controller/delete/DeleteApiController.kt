package com.example.mvc.controller.delete

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class DeleteApiController {

    @DeleteMapping("/delete-mapping")
    fun deleteMapping(
            @RequestParam(value = "name") _name:String,
            @RequestParam age:Int
    ):String{
        println(_name)
        println(age)
        return _name + " " + age
    }

    @DeleteMapping(path = ["delete-mapping/name/{name}/age/{age}"])
    fun pathDeleteMapping(@PathVariable(value = "name") _name:String, @PathVariable age:Int):String{
        println(_name)
        println(age)
        return _name + " " + age
    }
}