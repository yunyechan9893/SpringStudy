package com.example.springtodolist.repository

import com.example.springtodolist.database.ToDoDatabase
import com.example.springtodolist.database.Todo
import com.fasterxml.jackson.databind.JsonSerializer.None
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.stereotype.Service
import java.time.LocalDateTime



@Service
class TodoRepositoryImpl: TodoRepository {

    @Autowired
    lateinit var todoDataBase: ToDoDatabase

    override fun save(todo: Todo): Todo? {
        return todo.index?.let{index ->
            findOne(index)?.apply {
                this.title = todo.title
                this.description = todo.description
                this.schedule = todo.schedule
                this.updatedAt = LocalDateTime.now()
            }
        }?:kotlin.run {
            todo.apply {
                this.index = ++todoDataBase.index
                this.createdAt = LocalDateTime.now()
                this.updatedAt = LocalDateTime.now()
            }.run{
                todoDataBase.todoList.add(this)
                this
            }
        }
    }

    override fun saveAll(todoList: MutableList<Todo>): Boolean {
        return try{
            todoList.forEach{
                save(it)
            }
            true
        }catch (e:Exception){
            false
        }
    }


    override fun delete(index: Int): Boolean {
        return findOne(index)?.let {
            todoDataBase.todoList.remove(it)
            true
        } ?: false
    }
    override fun findOne(index: Int): Todo? {
        try {
            return todoDataBase.todoList.first { it.index == index }
        }catch (e:NoSuchElementException){
            return null
        }
    }

    override fun findAll(): MutableList<Todo> {
        return todoDataBase.todoList
    }
}