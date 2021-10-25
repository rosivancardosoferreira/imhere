package com.example.imhere

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.get
import java.util.*

class NewRegister : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private lateinit var backHome: LinearLayout
    private lateinit var options: Spinner
    private lateinit var buttonSave: Button
    private lateinit var dateHour: EditText

    //controls data
    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0

    var saveDay = 0
    var saveMonth = 0
    var saveYear = 0
    var saveHour = 0
    var saveMinute = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_register)
        this.buttonSave = findViewById<Button>(R.id.buttonSave)
        this.backHome = findViewById<LinearLayout>(R.id.backHome)
        this.options = findViewById<Spinner>(R.id.selectLocation)
        this.dateHour = findViewById<EditText>(R.id.dateHour)
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

        val listOptions = arrayOf("Dr. Dente de ouro", "Dra. Colmeia", "Futebol", "Banho quente");
        this.options.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listOptions)

        options.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                //Toast.makeText(applicationContext,options.getItemAtPosition(p2).toString(),Toast.LENGTH_SHORT).show() //EXAMPLE TOAST
                //result.text = options.get(p2).toString();
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                //result.text = "Selecione algo"
                //Toast.makeText(applicationContext,"SELECIONE ALGO!",Toast.LENGTH_SHORT).show()
            }
        }
        //back to home
        this.backHome.setOnClickListener {
            onBackPressed()
        }

        this.dateHour.setOnClickListener{
            getDateTimeCalendar()
            DatePickerDialog(this, this, year, month, day).show()
        }

        this.buttonSave.setOnClickListener {
            emdev()
        }

    }

    //calendar

    private fun getDateTimeCalendar() {
        val cal : Calendar = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.SHORT)
        minute = cal.get(Calendar.MINUTE)
    }
    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        saveDay = p3
        saveMonth = p2
        saveYear = p1

        getDateTimeCalendar()
        TimePickerDialog(this, this, hour, minute, true).show()
    }

    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
        saveHour = p1
        saveMinute = p2

        this.dateHour.setText("$saveDay/$saveMonth/$saveYear - $saveHour:$saveMinute");
        //+ $saveMonth + $saveYear + $saveHour + $saveMinute
    }

    private fun emdev() {
        Toast.makeText(applicationContext, "Em desenvolvimento!", Toast.LENGTH_SHORT).show();
    }
}