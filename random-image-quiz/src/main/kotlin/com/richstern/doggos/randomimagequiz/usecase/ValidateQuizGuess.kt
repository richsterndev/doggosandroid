package com.richstern.doggos.randomimagequiz.usecase

import com.richstern.doggos.model.Breed
import javax.inject.Inject

class ValidateQuizGuess @Inject constructor() {
    operator fun invoke(guess: String, breed: Breed): Boolean {
        val name = breed.name.replace("[^\\w]", "")
        val guessStripped = guess.replace("[^\\w]", "")
        return name.equals(guessStripped, true)
    }
}