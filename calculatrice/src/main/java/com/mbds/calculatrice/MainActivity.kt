package com.mbds.calculatrice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.mbds.calculatrice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(LayoutInflater.from(baseContext))

        binding.root.children.filter {
            it is Button
        }.forEach {
            it.setOnClickListener { button -> binding.form.text = binding.form.text.toString() + (button as Button).text
                /*when(button.tag?.toString()) {
                    "NUMBER" -> binding.form.text = binding.form.text.toString() + (button as Button).text
                }*/

            }
        }

    }


}