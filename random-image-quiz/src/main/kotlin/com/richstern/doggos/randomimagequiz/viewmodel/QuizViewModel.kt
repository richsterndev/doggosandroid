package com.richstern.doggos.randomimagequiz.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.richstern.doggos.randomimagequiz.statemachine.QuizEffect
import com.richstern.doggos.randomimagequiz.statemachine.QuizEvent
import com.richstern.doggos.randomimagequiz.statemachine.QuizState
import com.richstern.doggos.randomimagequiz.statemachine.QuizStateMachineFactory
import com.richstern.doggos.randomimagequiz.usecase.LoadRandomImage
import com.tinder.StateMachine
import kotlinx.coroutines.launch

class QuizViewModel @ViewModelInject constructor(
    private var loadRandomImage: LoadRandomImage,
    stateMachineFactory: QuizStateMachineFactory
) : ViewModel() {

    val quizState: LiveData<QuizState>
        get() = Transformations.distinctUntilChanged(_quizState)
    val quizEffect: LiveData<QuizEffect>
        get() = _quizEffect

    private val stateMachineListener = object :
        QuizStateMachineFactory.OnStateTransitionListener {
        override fun onStateTransition(
            transition: StateMachine.Transition.Valid<QuizState, QuizEvent, QuizEffect>
        ) {
            _quizState.value = transition.toState
            _quizEffect.value = transition.sideEffect
        }
    }

    private val stateMachine = stateMachineFactory.create(stateMachineListener)
    private val _quizState = MutableLiveData<QuizState>()
    private val _quizEffect = MutableLiveData<QuizEffect>()

    init {
        stateMachine.transition(QuizEvent.BeginLoadRandomImage)
    }

    fun load() {
        viewModelScope.launch {
            kotlin.runCatching {
                requireNotNull(loadRandomImage())
            }.onSuccess { breed ->
                stateMachine.transition(QuizEvent.LoadRandomImageSuccess(breed))
            }.onFailure { exception ->
                stateMachine.transition(QuizEvent.LoadRandomImageError(exception))
            }
        }
    }
}