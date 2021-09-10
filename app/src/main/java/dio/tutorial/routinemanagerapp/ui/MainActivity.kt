package dio.tutorial.routinemanagerapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dio.tutorial.routinemanagerapp.databinding.ActivityMainBinding

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
        initializeListeners()

    }

    private fun initializeListeners() {
        binding.fab.setOnClickListener {

            startActivity (
                    Intent(this, AddTaskActivity::class.java)
            )
        }
    }
}