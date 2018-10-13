package rs.adastra.anotta.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import rs.adastra.anotta.R
import rs.adastra.anotta.data.Note

class NoteListAdapter(context: Context) : RecyclerView.Adapter<NoteListAdapter.ViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

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
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // This could possibly be enhanced by using delegated or synthetic properties
        val titleTextView: TextView = itemView.findViewById(R.id.title_textview)
        val contentTextView: TextView = itemView.findViewById(R.id.content_textview)
    }
}