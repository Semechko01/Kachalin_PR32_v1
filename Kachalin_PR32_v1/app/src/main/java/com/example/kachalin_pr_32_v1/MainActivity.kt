//package com.example.kachalin_pr_32_v1
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//
//class MainActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//    }
//}
package com.example.kachalin_pr_32_v1


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    companion object {
        const val PREFS_NAME = "user_prefs"
        const val KEY_LOGIN = "login"
        const val KEY_PASSWORD = "password"

        const val DEFAULT_LOGIN = "ects"
        const val DEFAULT_PASSWORD = "ects2023"
    }

    private lateinit var etLogin: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etLogin = findViewById(R.id.etLogin)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)

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

        val prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val savedLogin = prefs.getString(KEY_LOGIN, null)
        val savedPassword = prefs.getString(KEY_PASSWORD, null)

        if (savedLogin == null || savedPassword == null) {
            if (login == DEFAULT_LOGIN && password == DEFAULT_PASSWORD) {
                prefs.edit()
                    .putString(KEY_LOGIN, login)
                    .putString(KEY_PASSWORD, password)
                    .apply()
                goToCalculator()
            } else {
                showAlert("Неверный логин или пароль")
            }
        } else {
            if (login == savedLogin && password == savedPassword) {
                goToCalculator()
            } else {
                showAlert("Неверный логин или пароль")
            }
        }
    }

    private fun goToCalculator() {
        val intent = Intent(this, OtherActivity2::class.java)
        startActivity(intent)

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