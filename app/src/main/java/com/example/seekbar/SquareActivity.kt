package com.example.seekbar


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

const val EXTRA_TAPPED_INSIDE_SQUARE = "com.example.seekbar.TAPPED_INSIDE_SQUARE"

class SquareActivity : AppCompatActivity() {

    private lateinit var container: ConstraintLayout
    private lateinit var squareImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_square)

        

        container = findViewById(R.id.container)
        squareImage = findViewById(R.id.square)

        var squareSize = intent.getIntExtra(EXTRA_SQUARE_SIZE, 100)
        val isEasyMode = intent.getBooleanExtra(EXTRA_EASY_MODE, false)
        // Sets a square with a 0 value to 1 so there is always something to try and click and
        // because a 0 is read as match constraints and would fill the whole screen.
        if (squareSize == 0) {
            squareSize = 1
        }
        // If easy mode is toggled we increase the size of the square by 2.
        if (isEasyMode){
            squareSize *= 2
        }

        squareImage.layoutParams.width = squareSize
        squareImage.layoutParams.height = squareSize


        squareImage.setOnClickListener {
            squareTapped(true)
        }

        container.setOnClickListener {
            squareTapped(false)
        }

    }

    private fun squareTapped(didTapSquare: Boolean) {
        Intent().apply {
            putExtra(EXTRA_TAPPED_INSIDE_SQUARE, didTapSquare)
            // sends if they tried to do the activity.
            setResult(RESULT_OK, this)
            finish()
        }

    }

}