package com.example.springtodolist.service

import com.example.springtodolist.config.AppConfig
import com.example.springtodolist.database.ToDoDatabase
import com.example.springtodolist.model.http.TodoDto
import com.example.springtodolist.repository.TodoRepositoryImpl
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [ToDoService::class, TodoRepositoryImpl::class, ToDoDatabase::class])
class ToDoServiceTest {

    @Autowired
    lateinit var todoService:ToDoService
    @Test
    fun create(){
        val result = todoService.create(
                TodoDto().apply{
                    this.title = "테스트"
                    this.description = "테스트 중"
                    this.schedule = "2023-05-22 10:22:11"
                }
        )

        println(result)
        Assertions.assertEquals(1, result?.index)
    }
}