package com.richstern.doggos.randomimagequiz.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.then
import com.richstern.doggos.core.network.DoggosService
import com.richstern.doggos.model.adapter.AdaptRandomImageResultToBreed
import com.richstern.doggos.model.api.ApiResponse
import com.richstern.doggos.randomimagequiz.coroutines.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.mockito.junit.MockitoJUnit
import org.mockito.quality.Strictness

@ExperimentalCoroutinesApi
class LoadRandomImageTest {

    @get:Rule
    val executorRule = InstantTaskExecutorRule()

    @get:Rule
    val strictRule = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val doggosService = mock<DoggosService>()
    private val adaptRandomImageResultToBreed = mock<AdaptRandomImageResultToBreed>()
    private val loadRandomImage = LoadRandomImage(
        doggosService = doggosService,
        adaptRandomImageResultToBreed = adaptRandomImageResultToBreed
    )

    @Test
    fun `given loadRandomImage returns message, should call adapt`() {
        runBlockingTest {
            // Given
            val message = "https://images.dog.ceo/breeds/finnish-lapphund/mochilamvan.jpg"
            val response = ApiResponse(
                message = message,
                status = "success"
            )
            given(doggosService.getRandomImage()).willReturn(response)

            // When
            loadRandomImage()

            // Then
            then(adaptRandomImageResultToBreed).should().invoke(message)
        }
    }
}