package com.richstern.doggos.randomimagequiz.view

import android.content.Context
import android.util.AttributeSet
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.richstern.doggos.randomimagequiz.R
import com.richstern.doggos.randomimagequiz.statemachine.QuizHistoryState

class QuizHistoryView(context: Context, attrs: AttributeSet) : SwipeRefreshLayout(context, attrs) {

    init {
        inflate(context, R.layout.view_quiz_history, this)
    }

    fun bind(quizHistoryState: QuizHistoryState.HistoryLoaded) {

    }
}