package com.starkcreativity.sharedpreferenceskt

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import com.starkcreativity.sharedpreferenceskt.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // to set text on text view from edit text
        binding.send.setOnClickListener{
            binding.textView.text = binding.editText.text.toString()
        }

        // to save the data of text view
        binding.save.setOnClickListener{
            // created object of SharedPreferences and assigned getSharedPreferences, give a name in 1st parameter and mode private so other apps can't access the data
            val sharedPreferences: SharedPreferences = getSharedPreferences("data", MODE_PRIVATE)
            val sharedPreferencesEditor: SharedPreferences.Editor = sharedPreferences.edit()  //  create a object of SharedPreferences.Editor as edit so we can use it's methods
            sharedPreferencesEditor.putString("text", binding.textView.text.toString())  // save text and give a name (key) for the value
            sharedPreferencesEditor.putBoolean("sw", binding.switch1.isChecked)
            sharedPreferencesEditor.apply()  // apply the changes
            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()  // making toast
        }

        getData()  // calling function
    }


    // function to get data from Shared Preferences
    private fun getData() {
        val s: String
        val b: Boolean
        val preferences: SharedPreferences = getSharedPreferences("data", MODE_PRIVATE) // created object
        s = preferences.getString("text", "").toString()
        b = preferences.getBoolean("sw", false)   // fetched data

        binding.textView.setText(s).toString()   // set data
        binding.switch1.isChecked = b
    }
}