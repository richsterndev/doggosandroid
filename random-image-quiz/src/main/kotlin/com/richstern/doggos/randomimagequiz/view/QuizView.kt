package com.richstern.doggos.randomimagequiz.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ScrollView
import com.richstern.doggos.randomimagequiz.R
import com.richstern.doggos.randomimagequiz.statemachine.QuizState

class QuizView(context: Context, attrs: AttributeSet) : ScrollView(context, attrs) {

    init {
        View.inflate(context, R.layout.view_quiz, this)
    }

    fun bind(quizState: QuizState.RandomImageLoaded) {
        Log.d("Rich", "bind: ${quizState.randomImage}")
    }
}