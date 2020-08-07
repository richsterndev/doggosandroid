package com.richstern.doggos.randomimagequiz.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.richstern.doggos.randomimagequiz.statemachine.QuizEffect
import com.richstern.doggos.randomimagequiz.statemachine.QuizEvent
import com.richstern.doggos.randomimagequiz.statemachine.QuizState
import com.richstern.doggos.randomimagequiz.statemachine.QuizStateMachineFactory
import com.richstern.doggos.randomimagequiz.usecase.LoadRandomImage
import com.tinder.StateMachine
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QuizViewModel @ViewModelInject constructor(
    private var loadRandomImage: LoadRandomImage,
    stateMachineFactory: QuizStateMachineFactory
) : ViewModel() {

    val quizState: LiveData<QuizState>
        get() = Transformations.distinctUntilChanged(_quizState)

    private val stateMachine = stateMachineFactory.create()
    private val _quizState = MutableLiveData<QuizState>()

    fun triggerEvent(event: QuizEvent) {
        val transition = stateMachine.transition(event)

        if (transition is StateMachine.Transition.Valid) {
            _quizState.value = stateMachine.state
            transition.sideEffect?.run {
                handleSideEffect(this)
            }
        }
    }

    fun load() {
        viewModelScope.launch {
            delay(1_000)
            kotlin.runCatching {
                requireNotNull(loadRandomImage())
            }.onSuccess { breed ->
                triggerEvent(QuizEvent.LoadRandomImageSuccess(breed))
            }.onFailure { exception ->
                triggerEvent(QuizEvent.LoadRandomImageError(exception))
            }
        }
    }

    private fun handleSideEffect(quizEffect: QuizEffect) {

    }
}