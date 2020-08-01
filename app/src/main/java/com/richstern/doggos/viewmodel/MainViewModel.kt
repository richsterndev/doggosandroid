package com.richstern.doggos.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.richstern.doggos.usecase.LoadRandomImage

class MainViewModel @ViewModelInject constructor(
    private val loadRandomImage: LoadRandomImage
) : ViewModel() {

}