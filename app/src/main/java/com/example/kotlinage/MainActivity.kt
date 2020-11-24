package com.example.kotlinage

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Locale.setDefault(Locale.KOREAN)

        btnDatePicker.setOnClickListener {view ->
            clickDatePicker(view)
        }
    }

    fun clickDatePicker(view: View) {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
       val dpd =  DatePickerDialog(this,DatePickerDialog.OnDateSetListener{view,selectedYear,selectedMonth,dayOfMonth
            ->
           val selectedDate = "$selectedYear/${selectedMonth+1}/$dayOfMonth"

            tvSelectDate.setText(selectedDate)

            val sdf = SimpleDateFormat("yyyy/MM/dd", Locale.KOREAN)

            val theDate =sdf.parse(selectedDate)

            val selectedDateInMinute = theDate!!.time

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

            val currentDateToMinutes = currentDate!!.time

            val differenceInMinutes = currentDateToMinutes - selectedDateInMinute

           val daycount = differenceInMinutes / (24 *60 *60 *1000)

            tvSelectInminute.setText(daycount.toString())
        }
            ,year,month,day)
        dpd.datePicker.setMaxDate(Date().time - 864000000)
        dpd.show()

    }
}