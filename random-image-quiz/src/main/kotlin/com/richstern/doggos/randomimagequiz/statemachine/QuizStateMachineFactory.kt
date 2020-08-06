package com.richstern.doggos.randomimagequiz.statemachine

import com.tinder.StateMachine
import javax.inject.Inject

class QuizStateMachineFactory @Inject constructor() {

    interface OnStateTransitionListener {
        fun onStateTransition(
            transition: StateMachine.Transition.Valid<QuizState, QuizEvent, QuizEffect>
        )
    }

    fun create(
        transitionListener: OnStateTransitionListener? = null
    ): StateMachine<QuizState, QuizEvent, QuizEffect> {
        return StateMachine.create {

            initialState(QuizState.Initialized)

            // Initialized State
            state<QuizState.Initialized> {

                on<QuizEvent.BeginLoadRandomImage> {
                    transitionTo(QuizState.Loading)
                }
            }

            // Loading State
            state<QuizState.Loading> {

                on<QuizEvent.LoadRandomImageSuccess> { event ->
                    transitionTo(QuizState.RandomImageLoaded(event.randomImage))
                }
            }
            
            // Loaded state
            state<QuizState.RandomImageLoaded> {
            }

            // On Transition listener
            onTransition { transition ->
                transitionListener?.apply {
                    (transition as? StateMachine.Transition.Valid)?.let { validTransition ->
                        onStateTransition(validTransition)
                    }
                }
            }
        }
    }
}