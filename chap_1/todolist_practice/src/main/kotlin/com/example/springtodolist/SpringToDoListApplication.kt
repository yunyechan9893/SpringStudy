package com.example.springtodolist

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringToDoListApplication

fun main(args: Array<String>) {
    runApplication<SpringToDoListApplication>(*args)
}
