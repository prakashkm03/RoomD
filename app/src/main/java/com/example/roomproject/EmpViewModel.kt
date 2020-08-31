package com.example.roomproject

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomproject.roomdb.EmpEntity
import com.example.roomproject.roomdb.EmpRepository
import com.example.roomproject.roomdb.Event
import kotlinx.coroutines.launch

class EmpViewModel(private val repository: EmpRepository) : ViewModel() , Observable {


    @Bindable
    val saveOrUpdateButtonText = MutableLiveData<String>()

    @Bindable
    val clearAllOrDeleteButtonText = MutableLiveData<String>()

    //for event
    private  val statusMessage = MutableLiveData<Event<String>>()
    val message : LiveData<Event<String>>
    get() = statusMessage


    init {
        saveOrUpdateButtonText.value = "Save"
        clearAllOrDeleteButtonText.value = "Clear All"
    }

    val employees = repository.employees

    @Bindable
    val inputName = MutableLiveData<String>()

    @Bindable
    val inputEmail = MutableLiveData<String>()

    private var isUpdateOrDelete = false
    private lateinit var empToUpdateOrDelete : EmpEntity



    fun saveOrUpdate()
    {
        if(isUpdateOrDelete)
        {
            empToUpdateOrDelete.name = inputName.value!!
            empToUpdateOrDelete.email = inputEmail.value!!
            update(empToUpdateOrDelete)
        }

        else {
            val name = inputName.value!!
            val email = inputEmail.value!!
            insert(EmpEntity(0, name, email))
            inputName.value = null
            inputEmail.value = null
        }

    }

    fun clearAllOrDelete()
    {
        if(isUpdateOrDelete)
        {
            delete(empToUpdateOrDelete)
        }
        else
        {
            clearAll()
        }
    }

    //to insert an employee
    fun insert(emp : EmpEntity)
    {
        viewModelScope.launch {
            repository.insert(emp)
            statusMessage.value = Event("Inserted Successfully")
        }
    }

    //job is written to make it more conscious else can use similar to insert function
    fun update(emp: EmpEntity)
    {
        viewModelScope.launch {
            repository.update(emp)
            inputName.value = null
            inputEmail.value = null
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"
            statusMessage.value = Event("Updated Successfully")
        }
    }

    fun delete(emp: EmpEntity)
    {
        viewModelScope.launch {
            repository.delete(emp)
            inputName.value = null
            inputEmail.value = null
            isUpdateOrDelete = false
            saveOrUpdateButtonText.value = "Save"
            clearAllOrDeleteButtonText.value = "Clear All"
            statusMessage.value = Event("Deleted Successfully")
        }
    }

    fun clearAll()
    {
        viewModelScope.launch {
            repository.deleteAll()
            statusMessage.value = Event("Cleared Successfully")
        }
    }

    fun initUpdateAndDelete(emp: EmpEntity)
    {
        inputName.value = emp.name
        inputEmail.value = emp.email
        isUpdateOrDelete = true
        empToUpdateOrDelete = emp
        saveOrUpdateButtonText.value = "Update"
        clearAllOrDeleteButtonText.value = "Delete"

    }





    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }


}