package com.richstern.doggos.randomimagequiz.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.richstern.doggos.randomimagequiz.R
import com.richstern.doggos.randomimagequiz.viewmodel.QuizHistoryViewModel
import com.richstern.doggos.randomimagequiz.viewmodel.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizHistoryFragment : Fragment() {

    private val quizHistoryViewModel: QuizHistoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quiz_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO: Init view model
        quizHistoryViewModel
    }

    companion object {
        fun newInstance(): QuizHistoryFragment {
            return QuizHistoryFragment()
        }
    }
}