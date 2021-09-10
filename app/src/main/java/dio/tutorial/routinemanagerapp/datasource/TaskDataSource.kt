package dio.tutorial.routinemanagerapp.datasource

import dio.tutorial.routinemanagerapp.model.Task

object TaskDataSource {
    private val list =  arrayListOf<Task>()

    fun getList() = list

    fun insertTask(task: Task) {
        list.add(task.copy(id = list.size + 1))
        onChange()
    }

    var onChange: () -> Unit = {}


}