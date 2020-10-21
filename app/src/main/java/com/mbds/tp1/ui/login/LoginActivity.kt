package com.mbds.tp1.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.mbds.tp1.MainActivity
import com.mbds.tp1.R
import com.mbds.tp1.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(LayoutInflater.from(baseContext))

        initView()

        val test = "toto"
        test.toUpper()
    }

    private fun initView(){

        binding.login.setOnClickListener {handleClick()}
    }

    private fun handleClick(){
        if(binding.email.text.isNotBlank() && binding.password.text.isNotBlank()){
            val intent = Intent(baseContext, MainActivity::class.java)
            startActivity(intent)
        }
        else{
            Toast.makeText(baseContext, "Veillez remplir tous les champs",Toast.LENGTH_LONG).show()
        }
    }

    fun String.toUpper() = this.toUpperCase()

}