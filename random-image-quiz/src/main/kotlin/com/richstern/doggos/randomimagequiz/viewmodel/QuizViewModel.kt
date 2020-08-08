package com.richstern.doggos.randomimagequiz.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.richstern.doggos.persistence.usecase.LoadAllBreeds
import com.richstern.doggos.randomimagequiz.statemachine.QuizEffect
import com.richstern.doggos.randomimagequiz.statemachine.QuizEvent
import com.richstern.doggos.randomimagequiz.statemachine.QuizState
import com.richstern.doggos.randomimagequiz.statemachine.QuizStateMachineFactory
import com.richstern.doggos.randomimagequiz.usecase.LoadRandomImage
import com.richstern.doggos.persistence.usecase.SaveBreed
import com.tinder.StateMachine
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class QuizViewModel @ViewModelInject constructor(
    private val loadRandomImage: LoadRandomImage,
    private val saveBreed: SaveBreed,
    private val loadAllBreeds: LoadAllBreeds,
    stateMachineFactory: QuizStateMachineFactory
) : ViewModel() {

    val quizState: LiveData<QuizState>
        get() = Transformations.distinctUntilChanged(_quizState)
    val quizEffect: LiveData<QuizEffect>
        get() = _quizEffect

    private val stateMachine = stateMachineFactory.create()
    private val _quizState = MutableLiveData<QuizState>()
    private val _quizEffect = MutableLiveData<QuizEffect>()

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

        viewModelScope.launch {
            val savedBreeds = loadAllBreeds()
            val count = savedBreeds.size
        }
    }

    private fun handleSideEffect(quizEffect: QuizEffect) {
        _quizEffect.value = quizEffect
        when (quizEffect) {
            is QuizEffect.SaveResult -> saveResult(quizEffect)
        }
    }

    private fun saveResult(quizEffect: QuizEffect.SaveResult) {
        viewModelScope.launch {
            kotlin.runCatching {
                saveBreed(quizEffect.randomImage)
            }
        }
    }
}