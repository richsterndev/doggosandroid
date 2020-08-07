package com.richstern.doggos.randomimagequiz.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.richstern.doggos.randomimagequiz.R
import com.richstern.doggos.randomimagequiz.statemachine.QuizState

class QuizSuccessView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    var listener: Listener? = null

    private val imageView by lazy { findViewById<ImageView>(R.id.quiz_success_image) }
    private val breedNameTextView by lazy { findViewById<TextView>(R.id.quiz_success_breed_name) }
    private val startOver by lazy { findViewById<View>(R.id.quiz_success_start_over) }

    init {
        inflate(context, R.layout.view_quiz_success, this)
        startOver.setOnClickListener {
            listener?.onStartOver()
        }
    }

    fun bind(quizState: QuizState.Success) {
        breedNameTextView.text = quizState.randomImage.name
        Glide.with(this)
            .load(quizState.randomImage.imageUrl)
            .centerCrop()
            .into(imageView)
    }

    interface Listener {
        fun onStartOver()
    }
}