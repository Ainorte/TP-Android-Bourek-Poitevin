package com.mbds.calculatrice

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import com.mbds.calculatrice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var nextOperation: Operation = Operation.NONE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView(){
        binding.root.children.filter {
            it is Button
        }.forEach {
            it.setOnClickListener { view ->
                val button = view as Button
                setListener(button)
            }
        }
    }


    private fun setListener(button: Button){
        when(button.tag?.toString()) {
            "NUMBER" -> binding.form.text = binding.form.text.toString() + (button as Button).text
            "AC" -> {
                binding.form.text = ""
                binding.res.text = ""
                nextOperation = Operation.NONE
            }
            "EQ" -> {
                val result = compute()

                binding.form.text = result.toString()
                binding.res.text = ""
                nextOperation = Operation.NONE
            }
            "PLUS" -> addOperation(Operation.ADD)
            "MINUS" -> addOperation(Operation.MIN)
            "DIV" -> addOperation(Operation.DIV)
            "MULT" -> addOperation(Operation.MUL)
            else -> {
                binding.form.text = ""
                binding.res.text = ""
                nextOperation = Operation.NONE
            }
        }
    }

    private fun addOperation(operation: Operation){
        val result = compute()

        binding.form.text = ""
        binding.res.text = result.toString()
        nextOperation = operation
    }

    private fun compute() :Double{
        var primaryOperator =  binding.res.text?.valueOrNull()?.toDouble() ?: 0.0
        val secondaryOperator =  binding.form.text?.valueOrNull()?.toDouble() ?: 0.0
        primaryOperator = when(nextOperation){
            Operation.ADD -> primaryOperator + secondaryOperator
            Operation.MIN -> primaryOperator - secondaryOperator
            Operation.DIV -> primaryOperator / secondaryOperator
            Operation.MUL -> primaryOperator * secondaryOperator
            Operation.NONE -> secondaryOperator
        }

        return primaryOperator
    }

    enum class Operation(){
        ADD,
        MIN,
        DIV,
        MUL,
        NONE
    }

    fun CharSequence.valueOrNull() : String? = if (isNullOrBlank()) null else toString()
}