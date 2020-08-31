package com.example.roomproject.roomdb

class EmpRepository(private val dao : EmpDao)
{

    val employees  = dao.getAllEmployees()

    suspend fun insert(emp : EmpEntity)  {
        dao.insertEmployee(emp)
    }

    suspend fun update(emp : EmpEntity)  {
        dao.updateEmployee(emp)
    }

    suspend fun delete(emp : EmpEntity)  {
        dao.deleteEmployee(emp)
    }

    suspend fun deleteAll()  {
        dao.deleteAll()
    }


}