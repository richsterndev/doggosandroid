package com.richstern.doggos.randomimagequiz.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.richstern.doggos.randomimagequiz.R
import com.richstern.doggos.randomimagequiz.statemachine.QuizHistoryEvent
import com.richstern.doggos.randomimagequiz.statemachine.QuizHistoryState
import com.richstern.doggos.randomimagequiz.view.QuizHistoryView
import com.richstern.doggos.randomimagequiz.view.QuizLoadingView
import com.richstern.doggos.randomimagequiz.viewmodel.QuizHistoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizHistoryFragment : Fragment() {

    private val quizHistoryViewModel: QuizHistoryViewModel by viewModels()
    private val quizHistoryView by lazy { view?.findViewById<QuizHistoryView>(R.id.quiz_history_view) }
    private val quizHistoryLoadingView by lazy {
        view?.findViewById<QuizLoadingView>(R.id.quiz_history_loading_view)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quiz_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        observeState()
        quizHistoryViewModel.triggerEvent(QuizHistoryEvent.BeginLoading)
    }

    private fun setListeners() {

    }

    private fun observeState() {
        quizHistoryViewModel.quizHistoryState.observe(
            viewLifecycleOwner,
            Observer { quizHistoryState ->
            when (quizHistoryState) {
                QuizHistoryState.Loading -> setLoadingState()
                is QuizHistoryState.HistoryLoaded -> setLoadedState(quizHistoryState)
                QuizHistoryState.HistoryRefreshing -> setRefreshingState()
            }
        })
    }

    private fun setLoadingState() {
        quizHistoryView?.isVisible = false
        quizHistoryLoadingView?.isVisible = true
    }

    private fun setLoadedState(quizHistoryState: QuizHistoryState.HistoryLoaded) {
        quizHistoryView?.let { view ->
            view.isVisible = true
            view.bind(quizHistoryState)
        }
        quizHistoryLoadingView?.isVisible = false
    }

    private fun setRefreshingState() {
        quizHistoryView?.isVisible = true
        quizHistoryLoadingView?.isVisible = false
    }

    companion object {
        fun newInstance(): QuizHistoryFragment {
            return QuizHistoryFragment()
        }
    }
}