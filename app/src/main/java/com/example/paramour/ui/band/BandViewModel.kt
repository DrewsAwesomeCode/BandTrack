package com.example.bandtrack.ui.band

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BandViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Bands Fragment"
    }
    val text: LiveData<String> = _text
}