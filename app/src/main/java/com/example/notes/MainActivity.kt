package com.example.notes

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.Adapter.NoteUserAdapter
import com.example.notes.Model.NoteUser
import com.example.notes.ViewModel.NoteUserViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var noteUserViewModel:NoteUserViewModel
    private lateinit var builder:AlertDialog.Builder
    private lateinit var dialog: AlertDialog
    private lateinit var name:EditText
    private lateinit var age:EditText
    private lateinit var save:Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var noteUseradapter: NoteUserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.rv_note_list)
        noteUseradapter = NoteUserAdapter(this,ArrayList<NoteUser>())
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = noteUseradapter
        }
        val fab: View = findViewById(R.id.fab)
        noteUserViewModel = ViewModelProvider(this).get(noteUserViewModel::class.java)
        noteUserViewModel.getNoteUserData(applicationContext)?.observe(this, Observer {
                noteUseradapter.setData(it as ArrayList<NoteUser>)
        })
        fab.setOnClickListener {
            openDialog()
        }
    }

    private fun openDialog() {
            builder = AlertDialog.Builder(this);
            var itemView: View = LayoutInflater.from(applicationContext).inflate(R.layout.save_dialog,null)
            dialog = builder.create()
            dialog.setView(itemView)
            name = itemView.findViewById(R.id.name1)
            age = itemView.findViewById(R.id.age1)
            save = itemView.findViewById(R.id.btn_save)
            save.setOnClickListener{
                saveDataInRoomDatabase()
            }
            dialog.show()
    }

    private fun saveDataInRoomDatabase(){
        val name = name.text.toString().trim()
        val age = age.text.toString().trim()
        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(age)){
            noteUserViewModel.insert(this, NoteUser(name,Integer.parseInt(age)))
        }else{
                Toast.makeText(applicationContext,"Please fill data", Toast.LENGTH_SHORT).show()
                dialog.dismiss()
        }
    }
}