package com.dough.myroutinestasks

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dough.myroutinestasks.aplication.CardTaskAplication
import com.dough.myroutinestasks.data.CardTask
import com.dough.myroutinestasks.databinding.ActivityMainBinding
import com.dough.myroutinestasks.ui.CardTaskAdapter
import com.dough.myroutinestasks.viewmodel.CardTaskViewModelFactory
import com.dough.myroutinestasks.viewmodel.TaskViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity(), CardTaskAdapter.OnItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private val adapter = CardTaskAdapter(this)
    private val taskViewModel: TaskViewModel by viewModels{
        CardTaskViewModelFactory((application as CardTaskAplication).repository)
    }
    private val newTaskActivityRequestCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainRecyclerView.adapter = adapter
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(this)

        setListenners()


        taskViewModel.allTasks.observe(this)  { tasks ->
            tasks?.let {
                adapter.submitList(it)
            }
        }



    }

    private fun setListenners() {

        binding.fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewTaskActivity::class.java)
            startActivityForResult(intent, newTaskActivityRequestCode)
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



    override fun onCheckBoxClick(task: CardTask, isChecked: Boolean){
        taskViewModel.updateTask(task, isChecked)
    }


}

