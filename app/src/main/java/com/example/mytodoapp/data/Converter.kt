package com.example.mytodoapp.data

import androidx.room.TypeConverter
import com.example.mytodoapp.data.model.Priority

class Converter {

    @TypeConverter
    fun fromPriority(priority: Priority): String{
        return priority.name
    }

    @TypeConverter
    fun fromString(priority: String): Priority {
        return Priority.valueOf(priority)
    }
}