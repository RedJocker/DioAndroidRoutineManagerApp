package dio.tutorial.routinemanagerapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import dio.tutorial.routinemanagerapp.databinding.ActivityAddTaskBinding
import dio.tutorial.routinemanagerapp.extensions.format
import java.util.*

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
        binding.tilDate.editText?.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")
            datePicker.addOnPositiveButtonClickListener {

                val timeZone = TimeZone.getDefault()
                val offset = timeZone.getOffset(Date().time) * -1

                binding.tilDate.editText?.setText(Date(it + offset).format())
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

            binding.btnCancel.setOnClickListener {
                finish()
            }
        }
    }
}