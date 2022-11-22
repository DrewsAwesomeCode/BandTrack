package com.example.bandtrack.ui.sets

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SetsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Sets Fragment"
    }
    val text: LiveData<String> = _text
}