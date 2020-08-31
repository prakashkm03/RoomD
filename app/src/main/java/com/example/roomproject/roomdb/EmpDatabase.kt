package com.example.roomproject.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [EmpEntity::class], version = 1)
abstract class EmpDatabase : RoomDatabase()
{

    abstract val empDao: EmpDao

    companion object{
        @Volatile
        private var INSTANCE : EmpDatabase? = null
        fun getInstance(context: Context):EmpDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(context.applicationContext, EmpDatabase::class.java,
                        "employee_data_database").build()
                }
                return instance
            }
        }

    }
}