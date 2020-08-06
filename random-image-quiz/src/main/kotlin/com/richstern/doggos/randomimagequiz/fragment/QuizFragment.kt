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
import com.richstern.doggos.randomimagequiz.statemachine.QuizState
import com.richstern.doggos.randomimagequiz.view.QuizErrorView
import com.richstern.doggos.randomimagequiz.view.QuizLoadingView
import com.richstern.doggos.randomimagequiz.view.QuizView
import com.richstern.doggos.randomimagequiz.viewmodel.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizFragment : Fragment() {

    private val quizViewModel: QuizViewModel by viewModels()
    private val quizView by lazy { view?.findViewById<QuizView>(R.id.quiz_view) }
    private val quizLoadingView by lazy { view?.findViewById<QuizLoadingView>(R.id.quiz_loading_view) }
    private val quizErrorView by lazy { view?.findViewById<QuizErrorView>(R.id.quiz_error_view) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        quizViewModel.quizState.observe(viewLifecycleOwner, Observer { quizState ->
            when (quizState) {
                is QuizState.Loading -> {
                    quizView?.isVisible = false
                    quizLoadingView?.isVisible = true
                    quizErrorView?.isVisible = false
                    quizViewModel.load()
                }
                is QuizState.RandomImageLoaded -> {
                    quizLoadingView?.isVisible = false
                    quizErrorView?.isVisible = false
                    quizView?.let { view ->
                        view.isVisible = true
                        view.bind(quizState)
                    }
                }
            }
        })
        quizViewModel.quizEffect.observe(viewLifecycleOwner, Observer { quizEffect ->

        })
    }

    companion object {
        fun newInstance(): QuizFragment {
            return QuizFragment()
        }
    }
}