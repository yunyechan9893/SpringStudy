package com.example.springtodolist.database

data class ToDoDatabase(
        var index:Int = 0,
        var todoList:MutableList<Todo> = mutableListOf()
){
    fun init(){
        this.index = 0
        this.todoList = mutableListOf()
        println("[Debug] Todo Database Init")
    }
}