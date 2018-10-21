package rs.adastra.anotta.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import rs.adastra.anotta.data.Note
import rs.adastra.anotta.data.NoteRepository

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = NoteRepository(application)
    val allNotes = repository.allNotes

    fun insert(note: Note) {
        repository.insert(note)
    }

    fun update(note: Note) {
        repository.update(note)
    }

    fun delete(note: Note) {
        repository.delete(note)
    }
}