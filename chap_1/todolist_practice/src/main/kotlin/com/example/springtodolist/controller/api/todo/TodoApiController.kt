package com.example.springtodolist.controller.api.todo

import com.example.springtodolist.model.http.TodoDto
import com.example.springtodolist.service.ToDoService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
@Tag(name = "일정관리")
@RestController
@RequestMapping("/api/todo")
class TodoApiController(
        val todoService: ToDoService
) {

    //R
    @Operation(summary = "문자열 반복",description = "일정확인")
    @Parameter(name = "int", description = "2번 반복할 문자열")
    @GetMapping(path = [""])
    fun read(@RequestParam(required=false) index:Int?): ResponseEntity<Any?> {

        return index?.let{
            todoService.read(it)
        }?.let{
            return ResponseEntity.ok(it)
        }?: kotlin.run {
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                    .header(HttpHeaders.LOCATION, "/api/todo/all")
                    .build()
        }
    }

    @GetMapping(path = ["all"])
    fun readAll(): MutableList<TodoDto> {
        return todoService.readAll()
    }

    //C
    @PostMapping(path = [""]) // 200
    fun create(@Valid @RequestBody todoDto: TodoDto): Any {
        return todoService.create(todoDto)?.let {
            ResponseEntity.status(HttpStatus.CREATED).body(it)
        }?:kotlin.run {
            ResponseEntity.badRequest().body("저장 실패")
        }
    }

    //U TODO Create = 201 내리도록, update = 200 내리도록
    @PutMapping(path = [""]) // 201
    fun update(@Valid @RequestBody todoDto: TodoDto): ResponseEntity<out Any> {
        return todoService.create(todoDto)?.let {
            ResponseEntity.status(HttpStatus.OK).body(it)
        }?:kotlin.run {
            ResponseEntity.badRequest().body("저장 실패")
        }
    }

    //D
    @DeleteMapping(path = ["/{index}"])
    fun delete(@PathVariable(name="index") _index:Int): ResponseEntity<Any> {
        if (!todoService.delete(_index)){
            return ResponseEntity.status(500).build()
        }

        return ResponseEntity.ok().build()

    }


}