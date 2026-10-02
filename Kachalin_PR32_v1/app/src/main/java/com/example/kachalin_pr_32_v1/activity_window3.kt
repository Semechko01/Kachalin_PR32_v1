package com.example.kachalin_pr_32_v1

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class activity_window3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_window3)
        val textCalc = findViewById<TextView>(R.id.TextSumCredit)
        val textCalc2 = findViewById<TextView>(R.id.TextSrokCredit)
        val textCalc3 = findViewById<TextView>(R.id.TextEsheMes)
        val textCalc4 = findViewById<Button>(R.id.ButtonExit)
        textCalc.setText(intent.getStringExtra("first3"))
        textCalc2.setText(intent.getStringExtra("first2"))
        textCalc3.setText(intent.getStringExtra("MONTHLY_PAYMENT"))
        textCalc4.setOnClickListener {
            val prefs = getSharedPreferences("user", Context.MODE_PRIVATE)
            prefs.edit()
                .putString("login", null)
                .putString("password", null)
                .apply()
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}