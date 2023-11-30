package com.example.springtodolist.service

import com.example.springtodolist.database.Todo
import com.example.springtodolist.database.convertTodo
import com.example.springtodolist.model.http.TodoDto
import com.example.springtodolist.model.http.convertTodoDto
import com.example.springtodolist.repository.TodoRepositoryImpl
import org.springframework.stereotype.Service

@Service
class ToDoService(
        val todoRepositoryImpl: TodoRepositoryImpl
) {

    //C
    fun create(todoDto: TodoDto):TodoDto?{
        return todoDto.let{
            Todo().convertTodo(it)
        }.let{
            todoRepositoryImpl.save(it)
        }?.let{
            TodoDto().convertTodoDto(it)
        }
    }

    //R
    fun read(index:Int): TodoDto? {
        return todoRepositoryImpl.findOne(index)?.let{
            TodoDto().convertTodoDto(it)
        }
    }

    fun readAll(): MutableList<TodoDto> {
        return todoRepositoryImpl.findAll()
                .map{
                    TodoDto().convertTodoDto(it)
                }.toMutableList()
    }

    //U
    fun update(todoDto: TodoDto): TodoDto? {
        return todoDto.let{
            Todo().convertTodo(it)
        }.let{
            todoRepositoryImpl.save(it)
        }?.let{
            TodoDto().convertTodoDto(it)
        }
    }

    //D
    fun delete(index:Int): Boolean{
        return todoRepositoryImpl.delete(index)
    }
}