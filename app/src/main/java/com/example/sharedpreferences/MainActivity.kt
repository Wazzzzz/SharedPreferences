package com.example.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val sharedPrefFile = "kotlinsharedpreference"
//    private lateinit var idEditText: EditText
//    private lateinit var nameEditText: EditText
//    private lateinit var idTextView: TextView
//    private lateinit var nameTextView: TextView
//    private lateinit var saveButton: Button
//    private lateinit var viewButton: Button
//    private lateinit var clearButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        setContentView(R.layout.activity_main)
//
//        idEditText = findViewById(R.id.id_edit_text)
//        nameEditText = findViewById(R.id.name_edit_text)
//        idTextView = findViewById(R.id.id_text_view)
//        nameTextView = findViewById(R.id.name_text_view)
//        saveButton = findViewById(R.id.save_button)
//        viewButton = findViewById(R.id.view_button)
//        clearButton = findViewById(R.id.clear_button)

        val sharedPreferences: SharedPreferences =
            this.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)

        binding.saveButton.setOnClickListener {
            val id: Int = Integer.parseInt(binding.idEditText.text.toString())
            val name: String = binding.nameEditText.text.toString()
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putInt("id_key", id)
            editor.putString("name_key", name)
            editor.apply()
            Toast.makeText(this, "Data Saved", Toast.LENGTH_SHORT).show()
        }

        binding.viewButton.setOnClickListener {
            val sharedIdValue = sharedPreferences.getInt("id_key", 0)
            val sharedNameValue = sharedPreferences.getString("name_key", "defaultname")
            if (sharedIdValue.equals(0) && sharedNameValue.equals("defaultname")) {
                binding.nameTextView.setText("default name: ${sharedNameValue}").toString()
                binding.idTextView.setText("default id: ${sharedIdValue.toString()}")
                Toast.makeText(this, "Data View Kosong", Toast.LENGTH_SHORT).show()
            }
            else {
                binding.nameTextView.setText(sharedNameValue).toString()
                binding.idTextView.setText(sharedIdValue.toString())
                Toast.makeText(this, "Data Ditampilkan", Toast.LENGTH_SHORT).show()
            }
        }

        binding.clearButton.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            binding.nameTextView.setText("")
            binding.idTextView.setText("")
            Toast.makeText(this, "Data Clear", Toast.LENGTH_SHORT).show()
        }

    }
}