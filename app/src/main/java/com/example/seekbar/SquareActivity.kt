package com.example.seekbar


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout

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
        if (squareSize == 0) {
            squareSize = 1
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