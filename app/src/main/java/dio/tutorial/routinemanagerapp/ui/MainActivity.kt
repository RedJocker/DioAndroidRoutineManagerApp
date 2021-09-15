package dio.tutorial.routinemanagerapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dio.tutorial.routinemanagerapp.databinding.ActivityMainBinding
import dio.tutorial.routinemanagerapp.datasource.TaskDataSource

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val adapter by lazy {
        TaskListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.rvTasks.adapter = adapter
        TaskDataSource.onChange = ::setListChanged
        setListChanged()
        initializeListeners()

    }

    private fun initializeListeners() {
        binding.fab.setOnClickListener {
            startActivity (
                    Intent(this, AddTaskActivity::class.java)
            )
        }

        adapter.listenerEdit = { task ->
            val intent = Intent(this, UpdateTaskActivity::class.java)
            intent.putExtra("oldTask", task)
            startActivity(intent)
        }
        adapter.listenerDelete = { task ->
            TaskDataSource.deleteTask(task)
        }
    }

    private fun setListChanged() {
        binding.rvTasks.adapter = adapter
        adapter.submitList(TaskDataSource.getList())
    }
}