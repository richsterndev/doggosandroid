package com.richstern.doggos.randomimagequiz.statemachine

import com.richstern.doggos.model.Breed

sealed class QuizState {
    object Initialized : QuizState()
    object Loading : QuizState()
    data class RandomImageLoaded(
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
}

sealed class QuizEffect {

}