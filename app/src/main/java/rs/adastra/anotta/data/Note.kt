package rs.adastra.anotta.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Note(
    @PrimaryKey
    val id:Int,
    var title: String,
    var content: String
)