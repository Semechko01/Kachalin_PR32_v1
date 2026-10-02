
package com.example.kachalin_pr_32_v1


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {



    private lateinit var etLogin: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etLogin = findViewById(R.id.etLogin)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        prefs = getSharedPreferences("user", Context.MODE_PRIVATE)
        val savedLogin = prefs.getString("login", null)
        val savedPassword = prefs.getString("password", null)

        if (savedLogin == "ects" && savedPassword == "ects2023") {
            startActivity(Intent(this, OtherActivity2::class.java))
        }
        btnLogin.setOnClickListener {
            handleLogin()
        }
    }

    private fun handleLogin() {
        val login = etLogin.text.toString()
        val password = etPassword.text.toString()


        if (login.length == 0 || password.length == 0 ) {
            showAlert("Введите логин и пароль")
            return
        }
        prefs.edit()
            .putString("login", login)
            .putString("password", password)
            .apply()
        startActivity(Intent(this, OtherActivity2::class.java))

    }


    private fun showAlert(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Ошибка")
            .setMessage(message)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .setCancelable(false)
            .show()
    }
}