package com.example.listadetareas.UI

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.listadetareas.DB.DBconexion
import com.example.listadetareas.DB.Note
import com.example.listadetareas.R

class NotesAdapter(private var notes: List<Note>, context: Context) : RecyclerView.Adapter<NotesAdapter.NoteViewHolder>(){

    private val db: DBconexion = DBconexion(context)

class  NoteViewHolder(itemview: View): RecyclerView.ViewHolder(itemview){
    val contentTextView: TextView = itemview.findViewById(R.id.contentTextView)
    val updateButton: ImageButton = itemview.findViewById(R.id.ibtnChange)
    val deleteButton: ImageButton = itemview.findViewById(R.id.ibtndelete)
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item__notes,parent,false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int = notes.size


    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
            val note = notes[position]
        holder.contentTextView.text = note.content

        holder.updateButton.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateNoteActivity::class.java).apply {
                putExtra("note_id", note.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.deleteButton.setOnClickListener {
            db.deleteNote(note.id)
            refreshData(db.getAllNotes())
            Toast.makeText(holder.itemView.context, "Nota Eliminada", Toast.LENGTH_SHORT).show()
        }



    }
fun refreshData(newNotes:List<Note>){

    notes = newNotes
    notifyDataSetChanged()


}

}