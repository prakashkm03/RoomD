package com.example.roomproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomproject.databinding.ListItemBinding
import com.example.roomproject.roomdb.EmpEntity

class RecyclerViewAdapter (private val emplist : List<EmpEntity>, private val listener: OnEmpItemClickListener) :
    RecyclerView.Adapter<RecyclerViewAdapter.viewholder>()
{
    inner class  viewholder(binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root) ,
            View.OnClickListener
    {
        val nametext : TextView = binding.nameTextView
        val emailtext : TextView = binding.emailTextView

        init {
            binding.listItemLayout.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            val currentitem = emplist[adapterPosition]
            listener.onEmpItemClick(currentitem)
        }
    }

    interface OnEmpItemClickListener{
        fun onEmpItemClick(emp: EmpEntity)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding : ListItemBinding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false)

        return viewholder(binding)
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {

        val currentitem = emplist[position]

        holder.nametext.text = currentitem.name
        holder.emailtext.text = currentitem.email

    }

    override fun getItemCount(): Int {
        return emplist.size
    }
}