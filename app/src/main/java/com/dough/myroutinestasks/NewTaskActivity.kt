package com.dough.myroutinestasks

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import com.dough.myroutinestasks.aplication.CardTaskAplication
import com.dough.myroutinestasks.data.CardTask
import com.dough.myroutinestasks.viewmodel.CardTaskViewModelFactory
import com.dough.myroutinestasks.viewmodel.TaskViewModel

class NewTaskActivity : AppCompatActivity() {

    private lateinit var editTaskView: EditText
    val taskViewModel: TaskViewModel by viewModels{
        CardTaskViewModelFactory((application as CardTaskAplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_task)
        editTaskView = findViewById(R.id.edit_word)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editTaskView.text)){
                setResult(Activity.RESULT_CANCELED, replyIntent)
            }else{
                val task = CardTask(
                id = intent.getIntExtra(EXTRA_REPLY,0),
                cardTitle = editTaskView.text.toString(),
                cardDescription = "",
                cardDate = "",
                cardHour = ""
                )
                taskViewModel.insert(task)
                setResult(Activity.RESULT_OK,replyIntent)

            }
            finish()
        }
    }

    companion object{
        const val EXTRA_REPLY = "com.dough.myroutinestasks.REPLY"
    }
}
