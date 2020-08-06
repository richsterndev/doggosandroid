package com.richstern.doggos.randomimagequiz.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.richstern.doggos.randomimagequiz.R
import com.richstern.doggos.randomimagequiz.statemachine.QuizState
import com.richstern.doggos.randomimagequiz.viewmodel.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizFragment : Fragment() {

    private val quizViewModel: QuizViewModel by viewModels()

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
                is QuizState.Loading -> quizViewModel.load()
                is QuizState.RandomImageLoaded -> {
                    Log.d("Rich", "load success: ${quizState.randomImage}")

                    // TODO: Hide other views
                    // TODO: Show random image view
                    // TODO: Bind random image iew
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