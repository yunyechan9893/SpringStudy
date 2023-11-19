package com.example.infrearnlecturepractice.advice

import com.example.infrearnlecturepractice.model.http.ErrorResponse
import com.example.infrearnlecturepractice.model.http.UserRequest
import com.example.infrearnlecturepractice.model.http._Error
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.ConstraintViolationException
import jakarta.validation.Valid
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Size
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import kotlin.math.min

@RestController
@RequestMapping("/api/exception")
@Validated
class ErrorApiController {

    @GetMapping("/hello")
    fun hello(
            @RequestParam
            @Size(min=2, max=5)
            name:String,

            @RequestParam
            @Min(value = 20)
            age:Int
    ): String {
        return "당신은 ${name}, 나이는${age} 입니다"
    }

    @PostMapping("/postHello")
    fun postHello(
            @Valid @RequestBody user:UserRequest
    ): UserRequest {
        return user
    }


    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun constraintViolationException(
            e:ConstraintViolationException, request:HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        var errors:MutableList<_Error> = mutableListOf()

        e.constraintViolations.forEach{
            var error:_Error = _Error().apply {
                this.message = it.message.toString()
                this.field = it.propertyPath.last().toString()
                this.requestValue = "너가 입력한 잘못된 값은 ${it.invalidValue} 이야"
            }

            errors.add(error)
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ErrorResponse().apply {
                    this.resultCode="flase"
                    this.httpStatus=HttpStatus.BAD_REQUEST.value().toString()
                    this.method=request.method.toString()
                    this.timestamp=LocalDateTime.now()
                    this.path=request.requestURI.toString()
                    this.errors= errors
                }
        )
    }


    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun methodArgumentNotValidException(e:MethodArgumentNotValidException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
        var errors:MutableList<_Error> = mutableListOf()

        e.bindingResult.allErrors.forEach{errorObject->
            var error = _Error().apply {
                this.field = (errorObject as FieldError).field
                this.message = errorObject.defaultMessage
                this.requestValue = (errorObject).rejectedValue
            }
            errors.add(error)
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                ErrorResponse().apply {
                    this.resultCode="flase"
                    this.httpStatus=HttpStatus.BAD_REQUEST.value().toString()
                    this.method=request.method.toString()
                    this.timestamp=LocalDateTime.now()
                    this.path=request.requestURI.toString()
                    this.errors= errors
                }
        )
    }
}