package com.example.training_okhttp_retrofit

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.training_okhttp_retrofit.adapter.NoteAdapter
import com.example.training_okhttp_retrofit.api.callback.ApiObserver
import com.example.training_okhttp_retrofit.api.callback.MockioError
import com.example.training_okhttp_retrofit.api.model.ListNoteReponse
import com.example.training_okhttp_retrofit.api.model.Note
import com.example.training_okhttp_retrofit.viewmodel.NoteViewModel

class MainActivity : AppCompatActivity() {

    var mListNoteRv: RecyclerView? = null
    var mNoteList: List<Note> = listOf()
    lateinit var mNoteAdapter: NoteAdapter

    private val mNoteViewModel by lazy {
        ViewModelProviders.of(this).get(NoteViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mListNoteRv = findViewById(R.id.listView)

        mNoteAdapter = NoteAdapter(this, mNoteList)
        mListNoteRv?.adapter = mNoteAdapter
        mListNoteRv?.layoutManager = LinearLayoutManager(this)

        val dividerItemDecoration = DividerItemDecoration(
            this,
            DividerItemDecoration.VERTICAL
        )

        mListNoteRv?.addItemDecoration(dividerItemDecoration)

        setSupportActionBar(findViewById(R.id.main_toolbar))


    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        var selected: Boolean = false
        when (item?.itemId) {
            R.id.action_refresh -> {
                refreshList()
                selected = true
            }
            else -> super.onOptionsItemSelected(item)
        }
        return selected
    }

    private fun userErrorHanlder(ex: Exception) {
        when (ex) {
            is MockioError.ErrorConfig.NetworkException -> {
                Toast.makeText(this@MainActivity, ex.message, Toast.LENGTH_LONG).show()
            }
            is MockioError.ErrorConfig.GitHubException -> {
                Toast.makeText(this@MainActivity, ex.message, Toast.LENGTH_LONG).show()
            }

            is MockioError.ErrorConfig.ForbiddenException -> {
                Toast.makeText(this@MainActivity, ex.message, Toast.LENGTH_LONG).show()
            }
            else -> Toast.makeText(this@MainActivity, "Oops! Something went wrong.", Toast.LENGTH_LONG).show()
        }
    }

    private fun refreshList() {
        mNoteViewModel.loadListNote()?.observe(this, object : ApiObserver<ListNoteReponse>(::userErrorHanlder) {
            override fun onSuccess(data: ListNoteReponse) {
                if (!data.listNote.isEmpty()) {
                    Toast.makeText(applicationContext, "List is loaded by network !! ", Toast.LENGTH_SHORT).show()
                    mNoteAdapter.updateListNote(data.listNote)
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.item_toolbar, menu)
        return true
    }
}
