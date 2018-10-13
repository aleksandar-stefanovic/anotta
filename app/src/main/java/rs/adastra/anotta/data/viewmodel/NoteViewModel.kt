package rs.adastra.anotta.data.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import rs.adastra.anotta.data.Note
import rs.adastra.anotta.data.NoteRepository

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = NoteRepository(application)
    val allNotes = repository.allNotes

    fun insert(note: Note) {
        repository.insert(note)
    }

}