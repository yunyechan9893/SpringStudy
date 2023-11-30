package com.example.springtodolist.config

import com.example.springtodolist.database.ToDoDatabase
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig{

    @Bean(initMethod = "init")
    fun todoDataBase(): ToDoDatabase {
        return ToDoDatabase()
    }
}