package com.example.springtodolist.repository

import com.example.springtodolist.config.AppConfig
import com.example.springtodolist.database.Todo
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.time.LocalDateTime

@ExtendWith(SpringExtension::class)
@SpringBootTest(classes = [TodoRepositoryImpl::class, AppConfig::class])
class TodoRepositoryTest {

    @Autowired
    lateinit var todoRepositoryImpl: TodoRepositoryImpl

    @BeforeEach
    fun before(){
        todoRepositoryImpl.todoDataBase.init()
    }

    @Test
    fun saveTest(){
        val todo = Todo().apply {
            this.title = "테스트 일정"
            this.description = "테스트"
            this.schedule = LocalDateTime.now()
        }

        val result = todoRepositoryImpl.save(todo)

        Assertions.assertEquals(1, result?.index)
        Assertions.assertNotNull(result?.createdAt)
        Assertions.assertNotNull(result?.updatedAt)
        Assertions.assertEquals("테스트 일정", result?.title)
        Assertions.assertEquals("테스트", result?.description)
    }

    @Test
    fun saveAllTest(){

        val todoList:MutableList<Todo> = mutableListOf(
                Todo().apply {
                    this.title = "테스트 1"
                    this.description = "테스트 중"
                    this.schedule = LocalDateTime.now()
                },
                Todo().apply {
                    this.title = "테스트 2"
                    this.description = "테스트 중"
                    this.schedule = LocalDateTime.now()
                }
        )

        val result = todoRepositoryImpl.saveAll(todoList)

        Assertions.assertEquals(true, result)
    }

    @Test
    fun findOneTest(){
        todoRepositoryImpl.save(Todo().apply {
            this.title = "테스트 일정"
            this.description = "테스트"
            this.schedule = LocalDateTime.now()
        })

        val todo:Todo? = todoRepositoryImpl.findOne(1)

        Assertions.assertNotNull(todo)
        Assertions.assertEquals(1, todo?.index)
        Assertions.assertEquals("테스트 일정", todo?.title)
        println(todo)
    }

    @Test
    fun deleteTest(){
        todoRepositoryImpl.save(Todo().apply {
            this.title = "테스트 일정"
            this.description = "테스트"
            this.schedule = LocalDateTime.now()
        })

        var result:Boolean = todoRepositoryImpl.delete(1)
        Assertions.assertEquals(true, result)
        Assertions.assertEquals(false, todoRepositoryImpl.delete(1))

    }

    @Test
    fun updateTest(){
        val todo = Todo().apply {
            this.title = "테스트 일정"
            this.description = "테스트"
            this.schedule = LocalDateTime.now()
        }

        val savedTodo = todoRepositoryImpl.save(todo)

        savedTodo?.title = "테스트 일정 수정"
        var updatedTodo = todoRepositoryImpl.save(todo)
        var listSize:Int = todoRepositoryImpl.todoDataBase.todoList.size

        Assertions.assertEquals("테스트 일정 수정", savedTodo?.title)
        Assertions.assertEquals(listSize, 1)
    }
}