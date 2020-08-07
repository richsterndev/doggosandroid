package com.richstern.doggos.randomimagequiz.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.richstern.doggos.randomimagequiz.R
import com.richstern.doggos.randomimagequiz.statemachine.QuizEvent
import com.richstern.doggos.randomimagequiz.statemachine.QuizState
import com.richstern.doggos.randomimagequiz.view.QuizErrorView
import com.richstern.doggos.randomimagequiz.view.QuizLoadingView
import com.richstern.doggos.randomimagequiz.view.QuizSuccessView
import com.richstern.doggos.randomimagequiz.view.QuizView
import com.richstern.doggos.randomimagequiz.viewmodel.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizFragment : Fragment() {

    private val quizViewModel: QuizViewModel by viewModels()
    private val quizView by lazy { view?.findViewById<QuizView>(R.id.quiz_view) }
    private val quizLoadingView by lazy { view?.findViewById<QuizLoadingView>(R.id.quiz_loading_view) }
    private val quizErrorView by lazy { view?.findViewById<QuizErrorView>(R.id.quiz_error_view) }
    private val quizSuccessView by lazy { view?.findViewById<QuizSuccessView>(R.id.quiz_success_view) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListeners()
        observeState()
        quizViewModel.triggerEvent(QuizEvent.BeginLoadRandomImage)
    }

    private fun observeState() {
        quizViewModel.quizState.observe(viewLifecycleOwner, Observer { quizState ->
            when (quizState) {
                is QuizState.Loading -> setLoadingState()
                is QuizState.RandomImageLoaded -> bindLoadedImage(quizState)
                is QuizState.HelpRequested -> displayHelpDialog()
                is QuizState.Success -> displaySuccess(quizState)
            }
        })
    }

    private fun displaySuccess(quizState: QuizState.Success) {
        quizView?.let { view ->
            view.isVisible = false
            view.reset()
        }
        quizSuccessView?.let { view ->
            view.isVisible = true
            view.bind(quizState)
        }
    }

    private fun displayHelpDialog() {
        context?.let { ctx ->
            AlertDialog.Builder(ctx).apply {
                setTitle("Need help with this breed?")
                setMessage("Click OK to solve this quiz or CANCEL to go back.")
                setPositiveButton("OK") { _, _ ->
                    quizViewModel.triggerEvent(QuizEvent.HelpConfirmed)
                }
                setNegativeButton("Cancel") { _, _ ->
                    quizViewModel.triggerEvent(QuizEvent.HelpDeclined)
                }
                setCancelable(false)
                show()
            }
        }
    }

    private fun bindLoadedImage(quizState: QuizState.RandomImageLoaded) {
        quizLoadingView?.isVisible = false
        quizErrorView?.isVisible = false
        quizSuccessView?.isVisible = false
        quizView?.let { view ->
            view.isVisible = true
            view.bind(quizState)
        }
    }

    private fun setLoadingState() {
        quizView?.isVisible = false
        quizLoadingView?.isVisible = true
        quizErrorView?.isVisible = false
        quizSuccessView?.isVisible = false
        quizViewModel.load()
    }

    private fun setListeners() {
        quizView?.listener = object : QuizView.Listener {
            override fun onHelpClicked() {
                quizViewModel.triggerEvent(QuizEvent.Help)
            }

            override fun onSubmitClicked(guess: String) {
                quizViewModel.triggerEvent(QuizEvent.Submit(guess))
            }
        }
        quizSuccessView?.listener = object : QuizSuccessView.Listener {
            override fun onStartOver() {
                quizViewModel.triggerEvent(QuizEvent.BeginLoadRandomImage)
            }
        }
    }

    companion object {
        fun newInstance(): QuizFragment {
            return QuizFragment()
        }
    }
}