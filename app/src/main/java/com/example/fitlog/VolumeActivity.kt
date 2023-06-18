package com.example.fitlog

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.Dialog
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.DatePicker
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DatePickerFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current date as the default date in the picker
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create a new instance of DatePickerDialog and return it
        return DatePickerDialog(
            activity as AppCompatActivity, activity as OnDateSetListener,
            year, month, day
        )
    }
}

class VolumeActivity : AppCompatActivity(), OnDateSetListener {
    private var menuName: String? = ""
    private var date = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_volume)

        menuName = intent.getStringExtra("menuName")
        val tvMenuName = findViewById<TextView>(R.id.tvMenuName)
        tvMenuName.text = menuName

        val tvPickedDate = findViewById<EditText>(R.id.etPickedDate)
        tvPickedDate.setText(date)
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        date = LocalDate.of(year, month + 1, day).format(DateTimeFormatter.BASIC_ISO_DATE)
        val tvPickedDate = findViewById<EditText>(R.id.etPickedDate)
        tvPickedDate.setText(date)
    }

    fun showDatePickerDialog() {
        val newFragment = DatePickerFragment()
        newFragment.show(supportFragmentManager, "datePicker")
    }

    fun onSaveButtonClick() {
        val edWeight = findViewById<EditText>(R.id.edWeight)
        val edRep = findViewById<EditText>(R.id.edRep)
        val edSet = findViewById<EditText>(R.id.edSet)
        val fitLog = FitLog(
            0,
            date,
            menuName,
            Integer.parseInt(edWeight.text.toString()),
            Integer.parseInt(edRep.text.toString()),
            Integer.parseInt(edSet.text.toString())
        )

        val fitLogDao = FitLogDatabase.getDatabase(this@VolumeActivity).fitLogDao()
        GlobalScope.launch() {
            fitLogDao.insert(fitLog)
        }

        Toast.makeText(applicationContext, "保存しました！", Toast.LENGTH_SHORT).show()

        finish()
    }
}