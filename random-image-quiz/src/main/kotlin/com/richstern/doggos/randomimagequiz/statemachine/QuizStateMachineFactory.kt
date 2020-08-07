package com.richstern.doggos.randomimagequiz.statemachine

import com.richstern.doggos.randomimagequiz.usecase.LoadRandomImage
import com.richstern.doggos.randomimagequiz.usecase.ValidateQuizGuess
import com.tinder.StateMachine
import javax.inject.Inject

class QuizStateMachineFactory @Inject constructor(
    private val validateQuizGuess: ValidateQuizGuess
) {

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
                    transitionTo(
                        QuizState.RandomImageLoaded(event.randomImage, resetView = true)
                    )
                }

                on<QuizEvent.LoadRandomImageError> { event ->
                    transitionTo(QuizState.RandomImageError(event.throwable))
                }
            }
            
            // Loaded state
            state<QuizState.RandomImageLoaded> {

                on<QuizEvent.Help> {
                    transitionTo(QuizState.HelpRequested(randomImage))
                }

                on<QuizEvent.HelpConfirmed> {
                    transitionTo(QuizState.RandomImageLoaded(randomImage, withHint = true))
                }

                on<QuizEvent.Submit> { event ->
                    if (validateQuizGuess(event.guess, randomImage)) {
                        transitionTo(QuizState.Success(randomImage))
                    } else {
                        transitionTo(
                            QuizState.RandomImageLoaded(randomImage, withIncorrectGuess = true)
                        )
                    }
                }
            }

            // Help Requested
            state<QuizState.HelpRequested> {

                on<QuizEvent.HelpConfirmed> {
                    transitionTo(QuizState.RandomImageLoaded(randomImage, withHint = true))
                }

                on<QuizEvent.HelpDeclined> {
                    transitionTo(QuizState.RandomImageLoaded(randomImage))
                }
            }

            // Success (correct guess)
            state<QuizState.Success> {

                on<QuizEvent.BeginLoadRandomImage> {
                    transitionTo(QuizState.Loading)
                }
            }
        }
    }
}