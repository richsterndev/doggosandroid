package com.richstern.doggos.randomimagequiz.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.richstern.doggos.persistence.usecase.LoadAllBreeds
import com.richstern.doggos.randomimagequiz.statemachine.QuizHistoryEffect
import com.richstern.doggos.randomimagequiz.statemachine.QuizHistoryEvent
import com.richstern.doggos.randomimagequiz.statemachine.QuizHistoryState
import com.richstern.doggos.randomimagequiz.statemachine.QuizHistoryStateMachineFactory
import com.tinder.StateMachine
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class QuizHistoryViewModel @ViewModelInject constructor(
    private val loadAllBreeds: LoadAllBreeds,
    quizHistoryStateMachineFactory: QuizHistoryStateMachineFactory
) : ViewModel() {

    val quizHistoryState: LiveData<QuizHistoryState>
        get() = Transformations.distinctUntilChanged(_state)
    val quizHistoryEffect: LiveData<QuizHistoryEffect>
        get() = Transformations.distinctUntilChanged(_effect)

    private val stateMachine = quizHistoryStateMachineFactory.create()
    private val _state = MutableLiveData<QuizHistoryState>()
    private val _effect = MutableLiveData<QuizHistoryEffect>()

    fun triggerEvent(event: QuizHistoryEvent) {
        val transition = stateMachine.transition(event)

        if (transition is StateMachine.Transition.Valid) {
            _state.value = stateMachine.state
            transition.sideEffect?.run {
                handleSideEffect(this)
            }
        }
    }

    fun load() {
        viewModelScope.launch {
            loadAllBreeds().collect { list ->
                triggerEvent(QuizHistoryEvent.LoadHistorySuccess(list))
            }
        }
    }

    private fun handleSideEffect(quizHistoryEffect: QuizHistoryEffect) {
        _effect.value = quizHistoryEffect
    }
}