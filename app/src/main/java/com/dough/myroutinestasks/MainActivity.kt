package com.dough.myroutinestasks

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dough.myroutinestasks.aplication.CardTaskAplication
import com.dough.myroutinestasks.ui.CardTaskAdapter
import com.dough.myroutinestasks.viewmodel.CardTaskViewModelFactory
import com.dough.myroutinestasks.viewmodel.TaskViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {

    private val taskViewModel: TaskViewModel by viewModels{
        CardTaskViewModelFactory((application as CardTaskAplication).repository)
    }
    private val newTaskActivityRequestCode = 1
    private var mainRecyclerView: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.main_recycler_view)
        val allTasks = taskViewModel.allCardsAndItemTasks
        val adapter = CardTaskAdapter(this,allTasks)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewTaskActivity::class.java)
            startActivityForResult(intent, newTaskActivityRequestCode)
        }

        taskViewModel.allCardsAndItemTasks.observe(this)  { tasks ->
            tasks?.let { adapter.submitList(it) }

        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newTaskActivityRequestCode && resultCode == Activity.RESULT_OK) {

        }else{
                Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
                ).show()
        }

    }

}

