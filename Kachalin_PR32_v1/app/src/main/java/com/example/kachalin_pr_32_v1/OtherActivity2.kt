
package com.example.kachalin_pr_32_v1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider

class OtherActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other2)

        val slider = findViewById<Slider>(R.id.myslider)
        val etTerm = findViewById<EditText>(R.id.etTerm)
        val etResult = findViewById<EditText>(R.id.etResult)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)



        btnCalculate.setOnClickListener {
            if(etTerm.text.toString().length == 0 || etResult.text.toString().length == 0)
            {
                showAlert("Пустая строка")
                return@setOnClickListener
            }
            val sumCredit = slider.value.toDouble()
            val termMonths = etTerm.text.toString().toIntOrNull()

            if (termMonths == null || termMonths <= 0) {
                showAlert("Введите срок в месяцах")
                return@setOnClickListener
            }

            val monthlyPayment = calculateMonthlyPayment(sumCredit, termMonths)

            val resultInThousands = monthlyPayment / 1000.0

            etResult.setText(String.format("%.2f тыс. руб.", resultInThousands))

            val intent = Intent(this, activity_window3::class.java)
            intent.putExtra("MONTHLY_PAYMENT", resultInThousands.toString())
            intent.putExtra("first2",etTerm.text.toString())
            intent.putExtra("first3",sumCredit.toString())
            startActivity(intent)
        }
    }


    private fun calculateMonthlyPayment(sumCredit: Double, termMonths: Int): Double {
        return when {

            termMonths <= 12 -> {
                sumCredit / termMonths + sumCredit * 0.059
            }

            termMonths <= 24 -> {
                val s1 = sumCredit / 12 + sumCredit * 0.059
                val paidFirstYear = s1 * 12

                sumCredit / termMonths + (sumCredit - paidFirstYear) * 0.051
            }

            else -> {
                val s1 = sumCredit / 12 + sumCredit * 0.059
                val paidFirstYear = s1 * 12

                val s2 = sumCredit / 24 + (sumCredit - paidFirstYear) * 0.051
                val paidTwoYears = paidFirstYear + s2 * 12

                sumCredit / termMonths + (sumCredit - paidTwoYears) * 0.042
            }
        }
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