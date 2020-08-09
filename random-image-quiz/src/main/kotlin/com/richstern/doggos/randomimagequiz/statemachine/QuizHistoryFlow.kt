package com.richstern.doggos.randomimagequiz.statemachine

import com.richstern.doggos.model.Breed

sealed class QuizHistoryState {
    object Initialized : QuizHistoryState()
    object Loading : QuizHistoryState()
    data class HistoryLoaded(
        val list: List<Breed>
    ) : QuizHistoryState()
    data class HistoryLoadError(
        val error: Throwable
    ) : QuizHistoryState()
    object HistoryRefreshing : QuizHistoryState()
}

sealed class QuizHistoryEvent {
    object BeginLoading : QuizHistoryEvent()
    data class LoadHistorySuccess(
        val list: List<Breed>
    ) : QuizHistoryEvent()
    data class LoadHistoryError(
        val error: Throwable
    ) : QuizHistoryEvent()
    object Refresh : QuizHistoryEvent()
}

sealed class QuizHistoryEffect {

}