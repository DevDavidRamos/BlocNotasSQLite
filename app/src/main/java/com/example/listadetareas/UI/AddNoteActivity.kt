package com.example.listadetareas.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.listadetareas.DB.DBconexion
import com.example.listadetareas.DB.Note
import com.example.listadetareas.databinding.ActivityAddNoteBinding

class AddNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNoteBinding
    private lateinit var db: DBconexion
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = DBconexion(this)

    binding.btnPublicar.setOnClickListener {
        val content = binding.txtPost.text.toString()
        val note = Note(0,content)
        db.insertNote(note)
        finish()
        Toast.makeText(this,"Nota guardada",Toast.LENGTH_SHORT).show()

    }

    }
}