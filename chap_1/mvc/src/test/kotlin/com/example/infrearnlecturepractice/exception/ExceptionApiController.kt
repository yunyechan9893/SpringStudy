package com.example.infrearnlecturepractice.exception

import com.example.infrearnlecturepractice.model.http.UserRequest
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.util.LinkedMultiValueMap

@WebMvcTest // web mvc 기능 관련 스프링부트만 로드
@AutoConfigureMockMvc // 자동으로 목 Mvc 설정을 하겠다
class ExceptionApiController {

    @Autowired // 자동으로 목 Mvc를 로드
    lateinit var mockMvc: MockMvc

    @Test
    fun helloTest(){
        mockMvc.perform(
            get("/api/exception/hello?name=윤예찬&age=28")
        ).andExpect{
            status().isOk
        }.andExpect(
            content().string("당신은 윤예찬, 나이는28 입니다")
        ).andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun helloBadRequestTest(){
        mockMvc.perform(
                get("/api/exception/hello?name=윤예찬&age=10")
        ).andExpect(
            status().isBadRequest
        ).andDo(print())
    }

    @Test
    fun helloApplicationsTest(){
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name", "윤예찬")
        queryParams.add("age", "28")

        mockMvc.perform(
                get("/api/exception/hello").queryParams(queryParams)
            ).andExpect(
                status().isOk()
            ).andExpect (
                content().string("당신은 윤예찬, 나이는28 입니다")
            ).andDo(
                print()
            )
    }

    @Test
    fun getFailTest(){
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name","윤")
        queryParams.add("age", "20")

        mockMvc.perform(
                get("/api/exception/hello").queryParams(queryParams)
        ).andExpect(
                status().isBadRequest
        ).andExpect(
                content().contentType("application/json")
        ).andExpect(
                jsonPath("\$.result_code").value("flase")
        ).andExpect(
                jsonPath("\$.errors[0].field").value("name")
        )
        .andDo(print())
    }

    @Test
    fun postHelloTest(){

        var userRequest = UserRequest().apply {
            this.name = "윤예찬"
            this.age = 28
            this.createAt="2023-11-19 12:52:00"
        }

        var json = jacksonObjectMapper().writeValueAsString(userRequest)

        mockMvc.perform(
                post("/api/exception/postHello")
                        .content(json)
                        .contentType("application/json")
                        .accept("application/json")
        ).andExpect(
                status().isOk
        ).andDo(print())
    }

    @Test
    fun postFailHelloTest(){

        var userRequest = UserRequest().apply {
            this.name = "윤예찬"
            this.age = -1
            this.createAt="2023-11-19 12:52:00"
        }

        var json = jacksonObjectMapper().writeValueAsString(userRequest)

        mockMvc.perform(
                post("/api/exception/postHello")
                        .content(json)
                        .contentType("application/json")
                        .accept("application/json")
        ).andExpect(
                status().`is`(400)
        ).andDo(print())
    }
}