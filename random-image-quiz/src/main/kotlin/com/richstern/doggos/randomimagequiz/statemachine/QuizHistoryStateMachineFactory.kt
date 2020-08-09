package com.richstern.doggos.randomimagequiz.statemachine

import com.tinder.StateMachine
import javax.inject.Inject

class QuizHistoryStateMachineFactory @Inject constructor() {
    fun create(): StateMachine<QuizHistoryState, QuizHistoryEvent, QuizHistoryEffect> {
        return StateMachine.create {
            initialState(QuizHistoryState.Initialized)

            // Initialized state
            state<QuizHistoryState.Initialized> {

                on<QuizHistoryEvent.BeginLoading> {
                    transitionTo(QuizHistoryState.Loading)
                }
            }

            // Loading state
            state<QuizHistoryState.Loading> {

                on<QuizHistoryEvent.LoadHistorySuccess> { event ->
                    transitionTo(QuizHistoryState.HistoryLoaded(event.list))
                }

                on<QuizHistoryEvent.LoadHistoryError> { event ->
                    transitionTo(QuizHistoryState.HistoryLoadError(event.error))
                }
            }

            // History loaded
            state<QuizHistoryState.HistoryLoaded> {

                on<QuizHistoryEvent.Refresh> {
                    transitionTo(QuizHistoryState.HistoryRefreshing)
                }
            }

            // History load error
            state<QuizHistoryState.HistoryLoadError> {

            }

            // Refreshing state
            state<QuizHistoryState.HistoryRefreshing> {

                on<QuizHistoryEvent.LoadHistorySuccess> { event ->
                    transitionTo(QuizHistoryState.HistoryLoaded(event.list))
                }

                on<QuizHistoryEvent.LoadHistoryError> { event ->
                    transitionTo(QuizHistoryState.HistoryLoadError(event.error))
                }
            }
        }
    }
}