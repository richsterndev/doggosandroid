package com.richstern.doggos.randomimagequiz.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.richstern.doggos.randomimagequiz.R

class QuizLoadingView(context: Context, attrs: AttributeSet) : ConstraintLayout(context, attrs) {

    init {
        View.inflate(context, R.layout.view_quiz_loading, this)
    }
}