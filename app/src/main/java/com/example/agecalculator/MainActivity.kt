package com.example.agecalculator
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*



class MainActivity : AppCompatActivity() {

    private var displayDate: TextView? = null
    private var diffDate:  TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  //User Interface: activity_main.xml


        val btn : Button = findViewById(R.id.btnDatePicker)

        displayDate = findViewById(R.id.date)
        diffDate = findViewById(R.id.diffDate)

        btn.setOnClickListener {

            clickDatePicker()
        }
    }

   private fun clickDatePicker(){
    //we are using DatePickerDialog class
        val calendar = Calendar.getInstance() //this will access to year, month, day etc
        var year = calendar.get(Calendar.YEAR)
        var month = calendar.get(Calendar.MONTH)
        var day = calendar.get(Calendar.DAY_OF_MONTH)
        val dpd =  DatePickerDialog(this, DatePickerDialog.OnDateSetListener
        { _, selectedYear, selectedMonth, selectedDayOfMonth ->

            Toast.makeText(this, "BTN is Pressed $year ${month+1}", Toast.LENGTH_LONG).show()

            val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"
            displayDate?.text = selectedDate

            //Converting the date into format of date or date
            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            //converting into selectedDate into date format
            val theDate = sdf.parse(selectedDate)

            //If it is empty it will not execute
            theDate?.let{
                val selectedDateInMinutes = theDate.time/ 60000

                //It will give me the time from the 1st January
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                currentDate?.let{
                    val currentDateInMinutes  = currentDate.time/60000

                    val diffInMinutes = currentDateInMinutes - selectedDateInMinutes

                    diffDate?.text = diffInMinutes.toString()
                }


            }

        },
        year, month, day
        )
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}