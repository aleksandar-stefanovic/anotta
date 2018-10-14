package rs.adastra.anotta.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Suppress("unused")
    @Delete
    fun delete(note: Note)

    @Query("SELECT * FROM Note ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("DELETE FROM Note")
    fun deleteAll()

}