package com.example.training_okhttp_retrofit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.training_okhttp_retrofit.api.callback.DataWrapper
import com.example.training_okhttp_retrofit.api.callback.NoteInteractor
import com.example.training_okhttp_retrofit.api.model.ListNoteReponse
import com.example.training_okhttp_retrofit.repository.Repository

class NoteViewModel() : ViewModel() {

    private val mListNote = MutableLiveData<ListNoteReponse>()
    private val mNoteRepo = Repository.of<ListNoteReponse>()

    fun loadListNote(): LiveData<DataWrapper<ListNoteReponse>>? {
        return mNoteRepo.getAll()
    }

    fun getListNote(): LiveData<ListNoteReponse> {
        return this.mListNote
    }

}