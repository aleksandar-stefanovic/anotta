package rs.adastra.anotta

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_edit_note.*
import rs.adastra.anotta.data.Note

class EditNoteActivity : AppCompatActivity() {

    private lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_note)

        viewModel = ViewModelProviders.of(this).get(ViewModel::class.java)

        // If not set, editing mode is initially false.
        if (viewModel.editMode.value == null) {
            viewModel.editMode.value = false
        }

        // TODO: create an observer and actually do something useful with editMode.

        // Checking whether there was a Note send by intent, otherwise using an empty Note
        val note = intent?.extras?.get(MainActivity.EXTRA_NOTE) as Note? ?: Note()

        edit_title.setText(note.title)
        edit_content.setText(note.content)

        button_save.setOnClickListener {
            val replyIntent = Intent()
            if (edit_title.isEmpty() && edit_content.isEmpty()) {
                setResult(RESULT_CANCELED, replyIntent)
            } else {
                note.title = edit_title.text?.toString() ?: ""
                note.content = edit_content.text?.toString() ?: ""
                replyIntent.putExtra(EXTRA_NOTE, note)
                setResult(RESULT_OK, replyIntent)
            }

            finish()
        }

        button_delete.setOnClickListener {
            val replyIntent = Intent()
            replyIntent.putExtra(EXTRA_NOTE, note)
            replyIntent.putExtra(EXTRA_DELETE, true)
            setResult(RESULT_OK, replyIntent)
            finish()
        }

        button_edit.setOnClickListener {
            viewModel.editMode.value = !viewModel.editMode.value!!
            Toast.makeText(this, "editMode: ${viewModel.editMode.value}", Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        const val EXTRA_NOTE = "rs.adastra.anotta.REPLY"
        const val EXTRA_DELETE = "rs.adastra.anotta.DELETE"

        private class ViewModel(application: Application) : AndroidViewModel(application) {
            val editMode: MutableLiveData<Boolean> by lazy {
                MutableLiveData<Boolean>()
            }
        }
    }

    private fun TextView.isEmpty() = TextUtils.isEmpty(this.text)
}
