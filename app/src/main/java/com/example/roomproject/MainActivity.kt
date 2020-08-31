package com.example.roomproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomproject.databinding.ActivityMainBinding
import com.example.roomproject.roomdb.EmpDatabase
import com.example.roomproject.roomdb.EmpEntity
import com.example.roomproject.roomdb.EmpRepository

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnEmpItemClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var empViewModel: EmpViewModel
    private lateinit var adapter: RecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val  dao = EmpDatabase.getInstance(application).empDao
        val repository = EmpRepository(dao)
        val factory = EmpViewModelFactory(repository)
        empViewModel = ViewModelProvider(this,factory).get(EmpViewModel::class.java)

        //Assigning the viewModel instance to the data binding object
        binding.myViewModel = empViewModel

        //Since i am using LiveData with DataBinding, i need to provide the lifeCycle Owner
        binding.lifecycleOwner = this

        initRecyclerView()

        empViewModel.message.observe(this, Observer {
            it.getContentIfNotHandled().let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        })


    }

    private fun initRecyclerView()
    {
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        displayEmployeesList()

    }


    //function to observe the list of employees data in database table
    private fun displayEmployeesList()
    {
        empViewModel.employees.observe(this, Observer {
            Log.i("PrakashTAG", it.toString())
            adapter = RecyclerViewAdapter(it, this)
            binding.recyclerView.adapter = adapter

        })
    }

    override fun onEmpItemClick(emp: EmpEntity) {
//        Toast.makeText(this, "Name is ${emp.name} and Email is ${emp.email}", Toast.LENGTH_SHORT).show()
        empViewModel.initUpdateAndDelete(emp)
    }
}