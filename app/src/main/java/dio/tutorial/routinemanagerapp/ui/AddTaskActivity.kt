package dio.tutorial.routinemanagerapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import dio.tutorial.routinemanagerapp.databinding.ActivityAddTaskBinding
import dio.tutorial.routinemanagerapp.datasource.TaskDataSource
import dio.tutorial.routinemanagerapp.extensions.format
import dio.tutorial.routinemanagerapp.model.Task
import java.util.TimeZone
import java.util.Date

class AddTaskActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityAddTaskBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initializeListeners()
    }

    private fun  initializeListeners() {
        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.btnCancel.setOnClickListener { finish() }

        binding.tilDate.editText?.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()

            datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")

            datePicker.addOnPositiveButtonClickListener {

                val timeZone = TimeZone.getDefault()
                val offset = timeZone.getOffset(Date().time) * -1

                binding.tilDate.editText?.setText(Date(it + offset).format())
            }
        }

        binding.tilHour.editText?.setOnClickListener {
            val timerPicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()

            timerPicker.addOnPositiveButtonClickListener {
                val hour = "%02d".format(timerPicker.hour)
                val minutes = "%02d".format(timerPicker.minute)
                binding.tilHour.editText?.setText("$hour:$minutes")
            }

            timerPicker.show(supportFragmentManager, "TIME_PICKER_TAG")
        }

        binding.btnNewTask.setOnClickListener {

            val task = Task(
                title = binding.tilTitle.editText?.text?.toString() ?: "",
                date = binding.tilDate.editText?.text?.toString() ?: "",
                hour = binding.tilHour.editText?.text?.toString() ?: ""
            )
            TaskDataSource.insertTask(task)
            finish()
        }
    }
}