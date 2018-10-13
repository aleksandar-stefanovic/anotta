package rs.adastra.anotta.data

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface NoteDao {

    @Insert
    fun insert(note: Note)

    @Suppress("unused")
    @Update
    fun update(note: Note)

    @Suppress("unused")
    @Delete
    fun delete(note: Note)

    @Query("SELECT * FROM Note ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Note>>
}