package com.example.listadetareas.UI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.listadetareas.DB.DBconexion
import com.example.listadetareas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

     private lateinit var binding: ActivityMainBinding
     private lateinit var db: DBconexion
    private lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        db = DBconexion(this)


        notesAdapter = NotesAdapter(db.getAllNotes(),this)

        binding.rvNote.layoutManager = LinearLayoutManager(this)
        binding.rvNote.adapter = notesAdapter



        binding.btnAddPost.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        notesAdapter.refreshData(db.getAllNotes())
    }
}