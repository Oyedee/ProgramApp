package com.example.programapp

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import com.example.programapp.databinding.ActivityMainBinding

const val EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE" //intent extra Id

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //let's handle when the user taps the display button
        binding.displayButton.setOnClickListener { sendMessage() }

        //Hide keyboard when user presses enter key
        binding.editName.setOnKeyListener { view, keyCode, _ ->
            handleKeyEvent(
                view,
                keyCode
            )
        }

    }

    /** Called when the user taps the display input button */
    private fun sendMessage() {
        val editText = findViewById<EditText>(R.id.edit_name)
        val message = editText.text.toString()
        if (message.isNotEmpty()) {
            val intent = Intent(this, ScreenActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, message)
            }
            startActivity(intent)
        } else {
            Toast.makeText(
                this,
                "Try again! Enter a valid input",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    //lunch Zuri's website when the user click on the ImageView
    fun displayZuriWebsite(view: View) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://internship.zuri.team/")
        startActivity(intent)
    }

    //Hide Keyboard when Enter key is pressed
    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }

    //close app when back button is pressed
    override fun onBackPressed() {
        super.onBackPressed()
        onDestroy()
    }
}