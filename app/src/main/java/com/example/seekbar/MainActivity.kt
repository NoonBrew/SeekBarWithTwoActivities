package com.example.seekbar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.SwitchCompat


const val EXTRA_SQUARE_SIZE = "com.example.seekbar.tap_the_square.SQUARE_SIZE"
const val EXTRA_EASY_MODE = "com.example.seekbar.tap_the_square.EASY_MODE"

class MainActivity : AppCompatActivity() {

    private lateinit var seekbar: SeekBar
    private lateinit var seekbarLabel: TextView
    private lateinit var showSquareButton: Button
    private lateinit var easyModeSwitch: Switch

    private val squareResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        result -> handleSquareResult(result)
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Grab our resource id.
        seekbar = findViewById(R.id.seek_bar)
        seekbarLabel = findViewById(R.id.seek_bar_label)
        showSquareButton = findViewById(R.id.show_square_button)
        easyModeSwitch = findViewById(R.id.easy_mode)
        // Progress is an attribute of seekbar. Gets an Int value.
        val initialProgress = seekbar.progress
        // Function passes the int to the text of our seekbar label.
        updateLabel(initialProgress)
        // Make a change listener, object is the seekbar. The first listener runs on start, the
        // second listener runs when the object is interacted with.
        seekbar.setOnSeekBarChangeListener( object: SeekBar.OnSeekBarChangeListener {
            // SeekBar is a nullable Object? fromUser?
            override fun onProgressChanged(seekbar: SeekBar?, progress: Int, fromUser: Boolean) {
                updateLabel(progress)
            }

            override fun onStartTrackingTouch(p0: SeekBar?) { }
            override fun onStopTrackingTouch(p0: SeekBar?) { }
        })


        showSquareButton.setOnClickListener {
            showSquare()
        }
    }

    private fun showSquare() {
        // launch SquareActivity
//        val showSquareIntent = Intent(this, SquareActivity::class.java)
//
//        showSquareIntent.putExtra(EXTRA_SQUARE_SIZE, seekbar.progress)
//
//        squareResultLauncher.launch(showSquareIntent)
        // tell SquareActivity how large the square should be
        // based on the progress of the Seekbar
        Intent(this, SquareActivity::class.java).apply {
            putExtra(EXTRA_SQUARE_SIZE, seekbar.progress)
            putExtra(EXTRA_EASY_MODE, easyModeSwitch.isChecked)
            squareResultLauncher.launch(this)
        }

    }

    private fun handleSquareResult(result: ActivityResult) {
        if(result.resultCode == RESULT_OK) {
            val intent = result.data
            val tapped = intent?.getBooleanExtra(EXTRA_TAPPED_INSIDE_SQUARE, false) ?: false
            val message = if (tapped) {
                "You tapped the square"
            } else {
                "You missed the square"
            }
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        } else if (result.resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "You did not try and tap the square", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateLabel(progress: Int) {
        seekbarLabel.text = getString(R.string.seekbar_value_message, progress)
    }
}