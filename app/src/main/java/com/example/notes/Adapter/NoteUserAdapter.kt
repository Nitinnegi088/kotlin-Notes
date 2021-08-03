package com.example.notes.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.Model.NoteUser
import com.example.notes.R

class NoteUserAdapter(private val context: Context, private var userList: ArrayList<NoteUser>) :
    RecyclerView.Adapter<NoteUserAdapter.UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_each_note,null))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user:NoteUser = userList[position]
        holder.name.text = user.name
        holder.age.text = user.age.toString()
    }

    fun setData(userList: ArrayList<NoteUser>){
        this.userList = userList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = userList.size

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
           val name:TextView = itemView.findViewById(R.id.name)
           val age: TextView = itemView.findViewById(R.id.age)
    }
}