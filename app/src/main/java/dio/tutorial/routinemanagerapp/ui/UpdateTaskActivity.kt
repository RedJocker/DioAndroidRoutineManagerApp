package dio.tutorial.routinemanagerapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import dio.tutorial.routinemanagerapp.databinding.ActivityUpdateTaskBinding
import dio.tutorial.routinemanagerapp.datasource.TaskDataSource
import dio.tutorial.routinemanagerapp.extensions.format
import dio.tutorial.routinemanagerapp.model.Task
import java.util.Date
import java.util.TimeZone

class UpdateTaskActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityUpdateTaskBinding.inflate(layoutInflater)
    }
    private val oldTask : Task? by lazy {
        val oldTask = intent.getSerializableExtra("oldTask")
        if(oldTask is Task) oldTask else null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val oldTask = oldTask
        if(oldTask == null) {
            finish()
        } else {
            binding.tilTitle.editText?.setText(oldTask.title)
            binding.tilDate.editText?.setText(oldTask.date)
            binding.tilHour.editText?.setText(oldTask.hour)
        }

        initializeListeners()
    }

    private fun  initializeListeners() {

        binding.toolbar.setNavigationOnClickListener { finish() }

        binding.btnCancel.setOnClickListener {
            finish()
        }

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

        binding.btnUpdateTask.setOnClickListener {
            val oldTask = oldTask
            if(oldTask != null) {
                val task = Task(
                        id = oldTask.id,
                        title = binding.tilTitle.editText?.text?.toString() ?: oldTask.title,
                        date = binding.tilDate.editText?.text?.toString() ?: oldTask.date,
                        hour = binding.tilHour.editText?.text?.toString() ?: oldTask.hour

                )
                TaskDataSource.updateTask(task)
                finish()
            }
        }
    }
}