package com.example.seekbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var seekbar: SeekBar
    private lateinit var seekbarLabel: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Grab our resource id.
        seekbar = findViewById(R.id.seek_bar)
        seekbarLabel = findViewById(R.id.seek_bar_label)
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
    }

    private fun updateLabel(progress: Int) {
        seekbarLabel.text = getString(R.string.seekbar_value_message, progress)
    }
}