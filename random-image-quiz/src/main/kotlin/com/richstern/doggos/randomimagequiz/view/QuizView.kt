package com.richstern.doggos.randomimagequiz.view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ScrollView
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.facebook.shimmer.ShimmerFrameLayout
import com.richstern.doggos.randomimagequiz.R
import com.richstern.doggos.randomimagequiz.statemachine.QuizState

class QuizView(context: Context, attrs: AttributeSet) : ScrollView(context, attrs) {

    var listener: Listener? = null

    private val shimmerView by lazy {
        findViewById<ShimmerFrameLayout>(R.id.quiz_view_image_shimmer)
    }
    private val inputTextView by lazy { findViewById<EditText>(R.id.quiz_view_breed_input) }
    private val helpButton by lazy { findViewById<View>(R.id.quiz_help) }
    private val imageView by lazy { findViewById<ImageView>(R.id.quiz_view_image) }
    private val submitButton by lazy { findViewById<View>(R.id.quiz_view_submit) }

    init {
        View.inflate(context, R.layout.view_quiz, this)
        helpButton.setOnClickListener {
            listener?.onHelpClicked()
        }
        submitButton.setOnClickListener {
            listener?.onSubmitClicked(inputTextView.text.toString())
        }
    }

    fun bind(quizState: QuizState.RandomImageLoaded) {
        displayShimmerView()
        Glide.with(this)
            .load(quizState.randomImage.imageUrl)
            .centerCrop()
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    hideShimmer()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    hideShimmer()
                    return false
                }
            })
            .into(imageView)

        if (quizState.withHint) {
            inputTextView.setText(quizState.randomImage.name)
        }
    }

    private fun displayShimmerView() {
        imageView.visibility = View.INVISIBLE
        shimmerView.visibility = VISIBLE
        shimmerView.startShimmer()
    }

    private fun hideShimmer() {
        shimmerView.stopShimmer()
        shimmerView.clearAnimation()
        shimmerView.visibility = INVISIBLE
        imageView.visibility = VISIBLE
    }

    interface Listener {
        fun onSubmitClicked(guess: String)
        fun onHelpClicked()
    }
}