package com.richstern.doggos.randomimagequiz.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.richstern.doggos.randomimagequiz.R

class QuizErrorView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    var listener: Listener? = null

    private val tryAgainButton by lazy { findViewById<View>(R.id.quiz_error_try_again) }

    init {
        View.inflate(context, R.layout.view_quiz_error, this)
        tryAgainButton.setOnClickListener {
            listener?.onTryAgain()
        }
    }

    interface Listener {
        fun onTryAgain()
    }
}