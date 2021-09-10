package dio.tutorial.routinemanagerapp.datasource

import dio.tutorial.routinemanagerapp.model.Task

object TaskDataSource {
    private val list =  arrayListOf<Task>()

    fun getList() = list.toList()

    fun insertTask(task: Task) {
        list.add(task.copy(id = list.size + 1))
        onChange()
    }

    fun updateTask(task: Task) {
        val index = list.indexOf(task)
        if(index >= 0) {
            list[index] = task
        }
        onChange()
    }

    var onChange: () -> Unit = {}


}