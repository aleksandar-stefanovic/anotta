package rs.adastra.anotta.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract val noteDao: NoteDao

    companion object {

        @Volatile
        private var INSTANCE: NoteDatabase? = null

        // Ugly, pasted Java code, could possibly be improved in the future.
        fun getDatabase(context: Context): NoteDatabase {
            if (INSTANCE == null) {
                synchronized(NoteDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            NoteDatabase::class.java, "word_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}