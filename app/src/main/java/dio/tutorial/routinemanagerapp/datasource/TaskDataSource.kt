package dio.tutorial.routinemanagerapp.datasource

import dio.tutorial.routinemanagerapp.model.Task

object TaskDataSource {
    var onChange: () -> Unit = {}
    private val idSequence: () -> Int = { var counter = 0; { counter++ } }()

    private val list =  arrayListOf<Task>()


    fun getList() = list.toList()

    fun insertTask(task: Task) {
        list.add(task.copy(id = idSequence()))
        onChange()
    }

    fun updateTask(task: Task) {
        val index = list.indexOf(task)
        if(index >= 0) {
            list[index] = task
        }
        onChange()
    }

    fun deleteTask(task: Task) {
        list.remove(task)
        onChange()
    }
}