package com.project.introduction

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var edtWidth : EditText
    private lateinit var edtHeight : EditText
    private lateinit var edtLength : EditText
    private lateinit var btnCalculate : Button
    private lateinit var tvResult : TextView

    companion object {
        private const val STATE_RESULT = "state_result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        edtHeight = findViewById(R.id.edt_height)
        edtLength = findViewById(R.id.edt_length)
        edtWidth = findViewById(R.id.edt_width)
        btnCalculate = findViewById(R.id.btn_calculate)
        tvResult = findViewById(R.id.tv_result)

        btnCalculate.setOnClickListener(this)

        if (savedInstanceState != null){
            val result = savedInstanceState.getString(STATE_RESULT)
            tvResult.text = result
        }
    }

    override fun onClick(v: View) {
        if (v.id == R.id.btn_calculate){
            val inputLength = edtLength.text.toString().trim()
            val inputWidth = edtWidth.text.toString().trim()
            val inputHeight = edtHeight.text.toString().trim()

            var isEmpy = false
            when{
                inputLength.isEmpty() -> {
                    isEmpy = true
                    edtLength.error = "Field ini tidak boleh kosong"
                    Toast.makeText(applicationContext, "Field ini tidak boleh kosong", Toast.LENGTH_SHORT).show()
                }
                inputWidth.isEmpty() -> {
                    isEmpy = true
                    edtWidth.error = "Field ini tidak boleh kosong"
                    Toast.makeText(applicationContext, "Field ini tidak boleh kosong", Toast.LENGTH_SHORT).show()
                }
                inputHeight.isEmpty() -> {
                    isEmpy = true
                    edtHeight.error = "Field ini tidak boleh kosong"
                    Toast.makeText(applicationContext, "Field ini tidak boleh kosong", Toast.LENGTH_SHORT).show()
                }
            }

            if (!isEmpy){
                val result = inputHeight.toDouble() * inputLength.toDouble() * inputWidth.toDouble()
                tvResult.text = result.toString()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }
}