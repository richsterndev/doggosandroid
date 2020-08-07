package com.richstern.doggos.randomimagequiz.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.ScrollView
import com.bumptech.glide.Glide
import com.richstern.doggos.randomimagequiz.R
import com.richstern.doggos.randomimagequiz.statemachine.QuizState

class QuizView(context: Context, attrs: AttributeSet) : ScrollView(context, attrs) {

    var listener: Listener? = null

    private val helpButton by lazy { findViewById<View>(R.id.quiz_help) }
    private val imageView by lazy { findViewById<ImageView>(R.id.quiz_view_image) }
    private val submitButton by lazy { findViewById<View>(R.id.quiz_view_submit) }

    init {
        View.inflate(context, R.layout.view_quiz, this)
        helpButton.setOnClickListener {
            listener?.onHelpClicked()
        }
        submitButton.setOnClickListener {
            listener?.onSubmitClicked()
        }
    }

    fun bind(quizState: QuizState.RandomImageLoaded) {
        Glide.with(this)
            .load(quizState.randomImage.imageUrl)
            .centerCrop()
            .into(imageView)
    }

    interface Listener {
        fun onSubmitClicked()
        fun onHelpClicked()
    }
}