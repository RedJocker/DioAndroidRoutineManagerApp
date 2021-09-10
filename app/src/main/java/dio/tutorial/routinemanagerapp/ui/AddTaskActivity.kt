package dio.tutorial.routinemanagerapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dio.tutorial.routinemanagerapp.R
import dio.tutorial.routinemanagerapp.databinding.ActivityAddTaskBinding

class AddTaskActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityAddTaskBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }


}