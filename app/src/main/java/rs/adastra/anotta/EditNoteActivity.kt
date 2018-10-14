package rs.adastra.anotta

import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_edit_note.*
import rs.adastra.anotta.data.Note

class EditNoteActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)

        button_save.setOnClickListener {
            val replyIntent = Intent()
            if (edit_title.isEmpty() && edit_content.isEmpty()) {
                setResult(RESULT_CANCELED, replyIntent)
            } else {
                val title = edit_title.text?.toString() ?: ""
                val content = edit_content.text?.toString() ?: ""
                val note = Note(0, title, content)
                replyIntent.putExtra(EXTRA_REPLY, note)
                setResult(RESULT_OK, replyIntent)
            }

            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "rs.adastra.anotta.REPLY"
    }

    private fun TextView.isEmpty() = TextUtils.isEmpty(this.text)
}
