package rs.adastra.anotta

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import rs.adastra.anotta.EditNoteActivity.Companion.EXTRA_DELETE
import rs.adastra.anotta.adapter.NoteListAdapter
import rs.adastra.anotta.data.Note
import rs.adastra.anotta.viewmodel.NoteViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(NoteViewModel::class.java)

        val noteListAdapter = NoteListAdapter(this)
        recyclerview.adapter = noteListAdapter
        recyclerview.layoutManager = LinearLayoutManager(this)

        viewModel.allNotes.observe(this, Observer<List<Note>> {
            if (it != null) {
                noteListAdapter.items = it
            }
        })

        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, EditNoteActivity::class.java)
            startActivityForResult(intent, NEW_NOTE_ACTIVITY_REQUEST_CODE)
        }

        noteListAdapter.onItemSelectedListener = { note ->
            val intent = Intent(this@MainActivity, EditNoteActivity::class.java)
            intent.putExtra(EXTRA_NOTE, note)
            startActivityForResult(intent, EDIT_NOTE_ACTIVITY_REQUEST_CODE)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            if (requestCode == NEW_NOTE_ACTIVITY_REQUEST_CODE) {
                val extras = data?.extras
                if (extras != null) {
                    val note = extras.get(EditNoteActivity.EXTRA_NOTE) as Note
                    viewModel.insert(note)
                }
            } else if (requestCode == EDIT_NOTE_ACTIVITY_REQUEST_CODE) {
                val extras = data?.extras
                if (extras != null) {
                    val note = extras.get(EditNoteActivity.EXTRA_NOTE) as Note

                    if (extras.getBoolean(EXTRA_DELETE)) {
                        viewModel.delete(note)
                    } else {
                        viewModel.update(note)
                    }
                }
            }
        }
    }

    companion object {
        const val EXTRA_NOTE = "rs.adastra.anotta.EXTRA_NOTE"
        const val NEW_NOTE_ACTIVITY_REQUEST_CODE = 1
        const val EDIT_NOTE_ACTIVITY_REQUEST_CODE = 2
    }
}
