package rs.adastra.anotta.data

import android.app.Application
import android.os.AsyncTask

class NoteRepository(application: Application) {

    private val db = NoteDatabase.getDatabase(application)
    private val noteDao = db.noteDao
    val allNotes = noteDao.getAllNotes()

    fun insert(note: Note) {
        InsertAsyncTask(noteDao).execute(note)
    }

    fun update(note: Note) {
        UpdateAsyncTask(noteDao).execute(note)
    }

    fun delete(note: Note) {
        DeleteAsyncTask(noteDao).execute(note)
    }

    companion object {
        // Android Studio recommends that AsyncTask should be a static class. I could have put it
        // outside of this class, but I chose to encapsulate it within the repository class.
        private class InsertAsyncTask(val noteDao: NoteDao) : AsyncTask<Note, Unit, Unit>() {
            override fun doInBackground(vararg params: Note) {
                noteDao.insert(params[0])
            }
        }

        private class UpdateAsyncTask(val noteDao: NoteDao) : AsyncTask<Note, Unit, Unit>() {
            override fun doInBackground(vararg params: Note) {
                noteDao.update(params[0])
            }
        }

        private class DeleteAsyncTask(val noteDao: NoteDao) : AsyncTask<Note, Unit, Unit>() {
            override fun doInBackground(vararg params: Note) {
                noteDao.delete(params[0])
            }
        }
    }
}
