package rs.adastra.anotta.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
class Note(
    @PrimaryKey
    val id:Int,
    var title: String,
    var content: String
)