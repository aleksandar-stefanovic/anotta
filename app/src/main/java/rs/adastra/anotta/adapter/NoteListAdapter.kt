package rs.adastra.anotta.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import rs.adastra.anotta.R
import rs.adastra.anotta.data.Note

class NoteListAdapter(context: Context) : androidx.recyclerview.widget.RecyclerView.Adapter<NoteListAdapter.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    var onItemSelectedListener: (note: Note) -> Unit = {}

    var items: List<Note> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = layoutInflater.inflate(R.layout.note_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = items[position]
        holder.titleTextView.text = note.title
        holder.contentTextView.text = note.content
        holder.root.setOnClickListener {
            onItemSelectedListener(note)
        }
    }

    class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        // This could possibly be enhanced by using delegated or synthetic properties
        val root: ViewGroup = itemView.findViewById(R.id.note_item)
        val titleTextView: TextView = itemView.findViewById(R.id.title_textview)
        val contentTextView: TextView = itemView.findViewById(R.id.content_textview)
    }
}