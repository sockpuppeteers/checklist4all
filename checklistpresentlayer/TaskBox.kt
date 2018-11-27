package com.example.doug.checklistpresentlayer

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

/********************************************
 *Class: TaskBox
 *
 *Purpose: Acts as a container for task text
 * in the main checklist view
 ********************************************/
class TaskBox @JvmOverloads constructor(
    context: Context,
    text: String,
    attrs: AttributeSet? = null,
    defStyle: Int = 0): LinearLayout(context, attrs, defStyle) {

    val taskText = text

    init
    {
        //setBackgroundColor(ContextCompat.getColor(context, R.color.colorTaskBackground))

        orientation = LinearLayout.HORIZONTAL

        val taskTextView = TextView(context)

        layoutParams = LinearLayout.LayoutParams(
          LinearLayout.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)

        gravity = right

        taskTextView.text = taskText
        taskTextView.textSize = 30f
        taskTextView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)

        addView(taskTextView)
    }
}