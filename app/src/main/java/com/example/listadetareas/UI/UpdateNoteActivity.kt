package com.example.listadetareas.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.listadetareas.DB.DBconexion
import com.example.listadetareas.DB.Note
import com.example.listadetareas.R
import com.example.listadetareas.databinding.ActivityAddNoteBinding
import com.example.listadetareas.databinding.ActivityUpdateNoteBinding

class UpdateNoteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateNoteBinding
    private lateinit var db: DBconexion
    private var noteId: Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = DBconexion(this)


        noteId = intent.getIntExtra("note_id", -1)
        if (noteId== -1){
            finish()
            return
        }

        val note  = db.getNoteByID(noteId)
        binding.txtUpdate.setText(note.content)

        binding.btnGuardar.setOnClickListener {
            val newContent = binding.txtUpdate.text.toString()
            val updateNote = Note(noteId, newContent)
            db.updateNotes(updateNote)
            finish()
            Toast.makeText(this,"Cambios Guardados",Toast.LENGTH_SHORT).show()
        }



    }
}