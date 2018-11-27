package com.example.doug.checklistpresentlayer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.history_popup.view.*
import kotlinx.android.synthetic.main.popup_layout.view.*



/********************************************
 *TO DO: Move listener assignments to functions
 ********************************************/
class BaseChecklist : AppCompatActivity(){

    var inEdit = false
    var currentChecklist = Checklist("Your Checklist")

    //Flag to see if any popups are present
    var popupPresent = false

    //Intialize things here
    init {

    }

    /********************************************
     *Purpose: Click Listener for the edit button
     *
     * DO NOT USE
     ********************************************/
    val edit_listener = View.OnClickListener {

        val task_scroll_layout = findViewById<LinearLayout>(R.id.TaskLayout)

        if(!inEdit)
        {

            var count = task_scroll_layout.getChildCount()

            for(i in 1..count) {
                (task_scroll_layout.getChildAt(i))
            }

            inEdit = true
        }
        else
        {
            var count = task_scroll_layout.getChildCount()

            for(i in 1..count) {
                ((task_scroll_layout.getChildAt(i)) as TaskBox).getChildAt(1).visibility = View.GONE
            }

            inEdit = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_checklist)

        val addButton = findViewById<Button>(R.id.AddTaskButton)
        val settingsButton = findViewById<Button>(R.id.SettingsButton)
        val historyButton = findViewById<Button>(R.id.HistoryButton)
        val editButton = findViewById<Button>(R.id.EditTaskButton)

        val addListener = View.OnClickListener {

            if(!popupPresent) {

                val mainView = findViewById<ScrollView>(R.id.TaskScrollView)

                val popupWindow = PopupWindow(this)

                val popupView = layoutInflater.inflate(R.layout.popup_layout, null)

                popupWindow.contentView = popupView

                val acceptButton = popupView.PopupMainView.AcceptButton

                acceptButton.setOnClickListener(View.OnClickListener {

                    val popup_edittext = popupView.PopupMainView.PopupEditText

                    val taskLayout = findViewById<LinearLayout>(R.id.TaskLayout)

                    var new_task_box = TaskBox(this,
                        popup_edittext.text.toString())

                    taskLayout.addView(new_task_box)

                    currentChecklist.createTask(popup_edittext.text.toString(), "enable Later", User(1))

                    popupWindow.dismiss()
                })

                val cancelButton = popupView.PopupMainView.CancelButton

                cancelButton.setOnClickListener(View.OnClickListener {

                    popupWindow.dismiss()

                })

                popupWindow.setOnDismissListener(PopupWindow.OnDismissListener {
                    val popupEdittext = popupView.PopupMainView.PopupEditText

                    popupEdittext.text.clear()

                    popupPresent = false
                })

                popupWindow.isFocusable = true

                popupWindow.showAtLocation(mainView, Gravity.CENTER, 0, 0)

                popupPresent = true

            }
        }

        addButton.setOnClickListener(addListener)

        settingsButton.setOnClickListener(View.OnClickListener {
            //Toast.makeText(this, "Hello there!", Toast.LENGTH_SHORT).show()

            Toast.makeText(this, "Functionality not implemented yet!", Toast.LENGTH_SHORT).show()
        })

        //Set history button's click listener

        val historyListener = View.OnClickListener {
            //Toast.makeText(this, "General Kenobi!", Toast.LENGTH_SHORT).show()

            if(!popupPresent) {

                val mainViewHistory = findViewById<ScrollView>(R.id.TaskScrollView)

                val popupWindowHistory = PopupWindow(this)

                val popupViewHistory = layoutInflater.inflate(R.layout.history_popup, null)

                popupWindowHistory.contentView = popupViewHistory


                val cancelListener = View.OnClickListener {
                    popupWindowHistory.dismiss()
                }

                val dismissListener = PopupWindow.OnDismissListener {
                    popupPresent = false
                }

                popupWindowHistory.setOnDismissListener(dismissListener)

                popupViewHistory.HistoryCloseButton.setOnClickListener(cancelListener)

                popupPresent = true

                popupWindowHistory.isFocusable = true

                val historyIterator = currentChecklist.changes.iterator()

                val historyLayout = popupViewHistory.HistoryLinearLayout

                //Check to see if not changes have happened
                if(currentChecklist.changes.isEmpty()) {
                    val checklistChangeTextView = TextView(this)

                    var toAddString = "No Changes in this checklist!"

                    checklistChangeTextView.text = toAddString

                    checklistChangeTextView.textSize = 10f
                    checklistChangeTextView.layoutParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                    )

                    historyLayout.addView(checklistChangeTextView)
                }
                else {
                    historyIterator.forEach {

                        val checklistChangeTextView = TextView(this)

                        var toAddString = "---Change of " + it.changeType + " to checklist " + it.changedTo + " to task " +
                                it.taskName + " by Current User."

                        checklistChangeTextView.text = toAddString

                        checklistChangeTextView.textSize = 10f
                        checklistChangeTextView.layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                        )

                        historyLayout.addView(checklistChangeTextView)
                    }
                }

                popupWindowHistory.showAtLocation(mainViewHistory, Gravity.CENTER, 0, 0)
            }
        }

        historyButton.setOnClickListener(historyListener)

        //editButton.setOnClickListener(edit_listener)

    }
}
