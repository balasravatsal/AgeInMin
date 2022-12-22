package com.vatsal.ageinmin

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {


//    private var tvSelectedDateData : TextView? = null
//    private var tvAgeInMinutes : TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btnDatePicker : Button = findViewById(R.id.buttonForDOB)

//        tvSelectedDateData = findViewById(R.id.SelectedDateData)
//        tvAgeInMinutes = findViewById(R.id.Result)

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }




    private fun clickDatePicker() {

        val myCalender = Calendar.getInstance()         // used for current dates
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        val tvSelectedDateData:TextView = findViewById(R.id.SelectedDateData)
        val tvAgeInMinutes:TextView = findViewById(R.id.Result)

        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{_, year, month, day ->
                Toast.makeText(this,
                    "Date of Birth Selected", Toast.LENGTH_LONG).show()


                val selectedDateData = "$day/${month+1}/$year"
                tvSelectedDateData?.text = selectedDateData

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDateData)
                theDate?.let {
                    val selectedDateInMinute = theDate.time / 60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {

                        val currentDateInMinutes = currentDate.time / 60000
                        val difInMin = currentDateInMinutes - selectedDateInMinute
                        tvAgeInMinutes?.text = difInMin.toString() }

                }

            },
            year,
            month,
            day             // giving 4 parameters
        )

//        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }


}