package com.richstern.doggos.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.richstern.doggos.usecase.LoadRandomImage
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    var loadRandomImage: LoadRandomImage
) : ViewModel() {

    fun load() {
        viewModelScope.launch {
            kotlin.runCatching {
                loadRandomImage()
            }.onSuccess {
                Log.d("Rich", "success: $it")
            }.onFailure {
                Log.d("Rich", "failure: $it")
            }
        }
    }
}