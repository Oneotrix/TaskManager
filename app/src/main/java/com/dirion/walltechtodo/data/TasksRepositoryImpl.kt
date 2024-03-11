package com.dirion.walltechtodo.data

import com.dirion.walltechtodo.domain.repository.TasksRepository
import com.dirion.walltechtodo.utils.TestData
import com.dirion.walltechtodo.view.ui.tasks.TaskModel
import javax.inject.Inject

class TasksRepositoryImpl: TasksRepository {
    override fun getTasks(): List<TaskModel> {
        return TestData.taskList
    }
}