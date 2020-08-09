package com.richstern.doggos.randomimagequiz.viewmodel

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.richstern.doggos.persistence.usecase.LoadAllBreeds
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class QuizHistoryViewModel @ViewModelInject constructor(
    loadAllBreeds: LoadAllBreeds
) : ViewModel() {
    init {
        viewModelScope.launch {
            loadAllBreeds().collect {
                Log.d("Rich", "Full list: $it")
            }
        }
    }
}