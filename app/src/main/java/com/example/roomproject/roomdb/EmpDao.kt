package com.example.roomproject.roomdb

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface EmpDao {

    @Insert
    suspend fun insertEmployee(emp: EmpEntity)

    @Update
    suspend fun updateEmployee(emp: EmpEntity)

    @Delete
    suspend fun deleteEmployee(emp: EmpEntity)

    @Query("DELETE FROM emp_table")
    suspend fun deleteAll() : Int

    @Query("SELECT * FROM emp_table")
    fun getAllEmployees(): LiveData<List<EmpEntity>>

}