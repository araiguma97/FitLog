package com.example.fitlog

import android.view.View
import android.widget.TextView
import com.kizitonwose.calendar.view.ViewContainer

class DayViewContainer(view: View) : ViewContainer(view) {
    val textView = view.findViewById<TextView>(R.id.calendarDayText)

    // val textView = CalendarDayLayoutBinding.bind(view).calendarDayText
}