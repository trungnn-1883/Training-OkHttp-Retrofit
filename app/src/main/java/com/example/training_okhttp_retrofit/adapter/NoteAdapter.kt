package com.example.training_okhttp_retrofit.adapter

import android.content.Context
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.training_okhttp_retrofit.api.model.Note
import com.example.training_okhttp_retrofit.R

class NoteAdapter(val mContext: Context, var mListNote: List<Note>) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mListNote.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.mTitleTxt?.text = mListNote[position].title ?: "No title"
        holder.mContentTxt?.text = mListNote[position].content ?: "No content"
//        holder.mRoot?.setOnCreateContextMenuListener(object : View.OnCreateContextMenuListener {
//            override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
//                addItemForMenu(menu, mListNote[position])
//            }
//
//        })
    }

    fun updateListNote(listNote: List<Note>?) {
        if (listNote != null) {
            this.mListNote = listNote
            notifyDataSetChanged()
        }
    }


    inner class NoteViewHolder(view: View) : RecyclerView.ViewHolder(view)/*, View.OnCreateContextMenuListener */ {

        var mTitleTxt: TextView? = null
        var mContentTxt: TextView? = null
        var mRoot: LinearLayout? = null

//        override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
//
//            menu?.apply {
//                setHeaderTitle("Select The Action")
//                // groupId, itemId, order, title
//                add(0, MainActivity.MENU_ITEM_VIEW, 0, "View Note")
//                add(0, MainActivity.MENU_ITEM_EDIT, 1, "Edit Note")
//                add(0, MainActivity.MENU_ITEM_DELETE, 2, "Delete Note")
//            }
//        }

        init {
            mTitleTxt = view.findViewById(R.id.item_note_title)
            mContentTxt = view.findViewById(R.id.item_note_content)
            mRoot = view.findViewById(R.id.item_note_root)
        }
    }

//    private fun addItemForMenu(menu: ContextMenu?, note: Note) {
//        menu?.setHeaderTitle("Select The Action")
//
//        menu?.add("Edit Note")?.setOnMenuItemClickListener(object : MenuItem.OnMenuItemClickListener {
//            override fun onMenuItemClick(item: MenuItem?): Boolean {
//                val intent = Intent(mContext, AddEditNoteActivity::class.java)
//                intent.putExtra("note", note)
//                (mContext as MainActivity).startActivityForResult(intent, MainActivity.MY_REQUEST_CODE)
//                return true
//            }
//
//        })
//
//        menu?.add("Delete Note")?.setOnMenuItemClickListener(object : MenuItem.OnMenuItemClickListener {
//            override fun onMenuItemClick(item: MenuItem?): Boolean {
//                AlertDialog.Builder(mContext)
//                    .setMessage("Note: " + note.title + "\nAre you sure you want to delete?")
//                    .setCancelable(false)
//                    .setPositiveButton(
//                        "Yes",
//                        DialogInterface.OnClickListener { dialog, id -> deleteNote(note) })
//                    .setNegativeButton("No", null)
//                    .show()
//                return true
//            }
//
//        })
//    }

//    private fun deleteNote(note: Note) {
//        val db = MyDataBaseHelper.getMyDataBaseHelper(mContext).noteDao()
//        db.deleteNode(note).subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                    Toast.makeText(mContext, "Delete successfully: " + it.toInt(), Toast.LENGTH_SHORT).show()
//            }, {
//                Toast.makeText(mContext, "Fail to delete, error = " + it, Toast.LENGTH_SHORT).show()
//            })
//    }
}