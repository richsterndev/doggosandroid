package com.richstern.doggos.randomimagequiz.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.richstern.doggos.randomimagequiz.usecase.LoadRandomImage
import kotlinx.coroutines.launch

class QuizViewModel @ViewModelInject constructor(
    var loadRandomImage: LoadRandomImage
) : ViewModel() {

    init {
        viewModelScope.launch {
            kotlin.runCatching {
                loadRandomImage()
            }.onSuccess {
                Log.d("Rich", "QuizViewModel loadRandomImage success: $it")
            }.onFailure {
                Log.d("Rich", "QuizViewModel loadRandomImage failure: $it")
            }
        }
    }
}