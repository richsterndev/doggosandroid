package com.richstern.doggos.randomimagequiz.statemachine

import com.richstern.doggos.model.Breed

sealed class QuizState {
    object Initialized : QuizState()
    object Loading : QuizState()
    data class RandomImageLoaded(
        val randomImage: Breed,
        var withHint: Boolean = false,
        var withIncorrectGuess: Boolean = false
    ) : QuizState()
    data class RandomImageError(
        val throwable: Throwable
    ) : QuizState()
    data class HelpRequested(
        val randomImage: Breed
    ) : QuizState()
    data class Success(
        val randomImage: Breed
    ) : QuizState()
}

sealed class QuizEvent {
    object BeginLoadRandomImage : QuizEvent()
    data class LoadRandomImageSuccess(
        val randomImage: Breed
    ) : QuizEvent()
    data class LoadRandomImageError(
        val throwable: Throwable
    ) : QuizEvent()
    object Help : QuizEvent()
    object HelpConfirmed : QuizEvent()
    object HelpDeclined : QuizEvent()
    data class Submit(
        val guess: String
    ) : QuizEvent()
}

sealed class QuizEffect {
}