package com.richstern.doggos.randomimagequiz.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.richstern.doggos.randomimagequiz.R
import com.richstern.doggos.randomimagequiz.statemachine.QuizHistoryState

class QuizHistoryView(context: Context, attrs: AttributeSet) : SwipeRefreshLayout(context, attrs) {

    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.quiz_history_list) }
    private val adapter = QuizHistoryAdapter()

    init {
        inflate(context, R.layout.view_quiz_history, this)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = adapter
    }

    fun bind(quizHistoryState: QuizHistoryState.HistoryLoaded) {
        isRefreshing = false
        adapter.setItems(quizHistoryState.list)
    }

    fun refresh() {
        isRefreshing = true
    }
}