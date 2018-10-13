package rs.adastra.anotta.data

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import android.os.AsyncTask


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
                            .addCallback(callback)
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }

        private val callback = object: RoomDatabase.Callback() {
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                PopulateDbAsync(INSTANCE!!).execute()
            }
        }

        class PopulateDbAsync(db: NoteDatabase): AsyncTask<Note, Unit, Unit>() {

            private val dao = db.noteDao

            override fun doInBackground(vararg params: Note) {
                dao.deleteAll()
                var note = Note(0, "This Is The Note Title", "Lorem ipsum dolor sit amet, id eam purto eius definitiones, persius probatus imperdiet sed ne. No cum assum mandamus splendide. Tantas persius diceret pri te. Qui alii erant semper cu. Sea lorem definitionem ut. His dictas option disputationi no, ex alterum voluptatum pri.")
                dao.insert(note)
                note = Note(1, "This Is Another Note Title", "This one is shorter.")
                dao.insert(note)
            }


        }
    }
}