package com.richstern.doggos.randomimagequiz.statemachine

import com.tinder.StateMachine
import javax.inject.Inject

class QuizStateMachineFactory @Inject constructor() {

    fun create(): StateMachine<QuizState, QuizEvent, QuizEffect> {
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

                on<QuizEvent.LoadRandomImageError> { event ->
                    transitionTo(QuizState.RandomImageError(event.throwable))
                }
            }
            
            // Loaded state
            state<QuizState.RandomImageLoaded> {
            }
        }
    }
}