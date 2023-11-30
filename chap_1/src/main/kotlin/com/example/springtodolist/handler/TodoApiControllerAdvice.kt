package com.example.springtodolist.handler

import com.example.springtodolist.controller.api.todo.TodoApiController
import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import com.example.springtodolist.model.http.Error
import com.example.springtodolist.model.http.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import java.time.LocalDateTime

@ControllerAdvice(basePackageClasses = [TodoApiController::class])
class TodoApiControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(e: MethodArgumentNotValidException, request:HttpServletRequest) {
        var errors:MutableList<Error> = mutableListOf()
        e.bindingResult.allErrors.forEach{
            val error = Error().apply {
                this.field = (it as FieldError).field
                this.message = it.defaultMessage
                this.value = it.rejectedValue
            }

            errors.add(error)
        }
        return ErrorResponse().apply {
            this.http_status = "Fail"
            this.result_code = HttpStatus.BAD_REQUEST.value().toString()
            this.httpMethod = request.method
            this.message = ""
            this.path = request.requestURI
            this.timestamp = LocalDateTime.now()
            this.errors = errors
        }.run {
            ResponseEntity.badRequest().body(this)
        }
    }

}