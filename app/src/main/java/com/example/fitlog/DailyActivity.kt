package com.example.fitlog

import android.os.Bundle
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class DailyActivity : AppCompatActivity() {
    private var date = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily)

        date = intent.getStringExtra("date")
        val tvDate = findViewById<TextView>(R.id.tvDate)
        tvDate.text = date

        val fitLogDao = FitLogDatabase.getDatabase(this@DailyActivity).fitLogDao()
        var fitLogList = runBlocking {
            fitLogDao.getFitLogList(date)
        }

        val destFitLogList: MutableList<MutableMap<String?, String?>> = mutableListOf()
        for (fitLog in fitLogList) {
            var term = fitLog.date + " " + fitLog.menuName + " (" + fitLog.weight.toString() + " kg)"
            var description = fitLog.reps.toString() + "回 " + fitLog.reps.toString() + "セット "
            var destFitLog: MutableMap<String?, String?> = mutableMapOf("term" to term, "description" to description)
            destFitLogList.add(destFitLog)

        }

        val from = arrayOf("term", "description")
        val to = intArrayOf(android.R.id.text1, android.R.id.text2)
        val adapter = SimpleAdapter(
            this@DailyActivity,
            destFitLogList,
            android.R.layout.simple_list_item_2,
            from,
            to
        )

        val lvFitLogList = findViewById<ListView>(R.id.lvFitLogList)
        lvFitLogList.adapter = adapter
    }
}