package com.example.roomproject.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "emp_table")
data class EmpEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "emp_id")
    var id : Int,

    @ColumnInfo(name = "emp_name")
    var name : String,

    @ColumnInfo(name = "emp_email")
    var email : String

)