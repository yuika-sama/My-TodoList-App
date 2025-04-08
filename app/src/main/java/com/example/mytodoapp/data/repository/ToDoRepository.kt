package com.example.mytodoapp.data.repository

import androidx.lifecycle.LiveData
import com.example.mytodoapp.data.ToDoDao
import com.example.mytodoapp.data.model.ToDoData

class ToDoRepository(private val toDoDao: ToDoDao) {
    val getAllData: LiveData<List<ToDoData>> = toDoDao.getAllData()

    suspend fun insertData(toDoData: ToDoData) {
        toDoDao.insertData(toDoData)
    }

    suspend fun deleteAll() {
        toDoDao.deleteAllData()
    }

    suspend fun updateData(toDoData: ToDoData){
        toDoDao.updateData(toDoData)
    }

    suspend fun deleteItem(toDoDaTa: ToDoData){
        toDoDao.deleteItem(toDoDaTa)
    }

}