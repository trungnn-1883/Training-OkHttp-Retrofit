package com.example.training_okhttp_retrofit

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.training_okhttp_retrofit.adapter.NoteAdapter
import com.example.training_okhttp_retrofit.api.client.APIClient
import com.example.training_okhttp_retrofit.api.model.ListNoteReponse
import com.example.training_okhttp_retrofit.api.model.Note
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var mListNoteRv: RecyclerView? = null
    var mNoteList: List<Note> = listOf()
    lateinit var mNoteAdapter: NoteAdapter

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

    private fun refreshList() {
        APIClient.getAllNote()?.enqueue(object : Callback<ListNoteReponse> {

            override fun onFailure(call: Call<ListNoteReponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<ListNoteReponse>, response: Response<ListNoteReponse>) {
                if (response.isSuccessful) {
                    Log.d("Call api", "Successfully")
                    mNoteAdapter.updateListNote(response.body()?.listNote)
                }
            }

        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.item_toolbar, menu)
        return true
    }
}
