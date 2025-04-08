package com.example.mytodoapp.data.model

import android.annotation.SuppressLint
import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize

@Entity(tableName = "todo_table")
@Parcelize
data class ToDoData(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var priority: Priority,
    var description: String
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString().toString(),
        TODO("priority"),
        parcel.readString().toString()
    )

    companion object : Parceler<ToDoData> {

        override fun ToDoData.write(parcel: Parcel, flags: Int) {
            parcel.writeInt(id)
            parcel.writeString(title)
            parcel.writeString(priority.toString())
            parcel.writeString(description)
        }

        override fun create(parcel: Parcel): ToDoData {
            return ToDoData(parcel)
        }
    }
}