package com.example.roomproject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomproject.roomdb.EmpRepository
import java.lang.IllegalArgumentException

class EmpViewModelFactory(private val repository: EmpRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(EmpViewModel::class.java)){
            return EmpViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }

}