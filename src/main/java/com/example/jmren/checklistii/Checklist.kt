package com.example.jmren.checklistii
import java.time.LocalDateTime

class Checklist(var tasks: MutableList<Task>, var users: MutableList<User>,
                var changes: MutableList<Change>) {
    fun logChange(taskChanged: String, changedBy: User, changeType: kAction, changedTo: String) {
        val change = Change(taskChanged, changedBy, changeType, changedTo)
        changes.add(change)
    }
    fun logChange(taskChanged: String, changedBy: User, changeType: kAction) {
        val change = Change(taskChanged, changedBy, changeType)
        changes.add(change)
    }

    fun createTask(name: String, description: String, createdBy: User) {
        val task = Task(name, description)
        tasks.add(task)
        logChange(name, createdBy, kAction.CREATE_TASK, name)
    }

    fun createTask(name: String, description: String, deadline: String, createdBy: User) {
        val task = Task(name, description, deadline)
        tasks.add(task)
        logChange(name, createdBy, kAction.CREATE_TASK, name)
    }

    fun completeTask(taskCompleted: Task, completedBy: User) {
        for (task in tasks) {
            if (task.name == taskCompleted.name) {
                task.completed = true
                task.compby = completedBy
                task.compdatetime = LocalDateTime.now().toString()
                logChange(task.name, completedBy, kAction.COMPLETE_TASK)
            }
        }
    }

    //This function allows the user to manually enter the date/time a task was completed on
    fun completeTask(taskCompleted: Task, completedBy: User, datetimeWhenCompleted: String) {
        for (task in tasks) {
            if (task.name == taskCompleted.name) {
                task.completed = true
                task.compby = completedBy
                task.compdatetime = datetimeWhenCompleted
                logChange(task.name, completedBy, kAction.COMPLETE_TASK)
            }
        }
    }

    fun deleteTask(taskDeleted: Task, deletedBy: User) {
        for (task in tasks) {
            if (task.name == taskDeleted.name) {
                logChange(task.name, deletedBy, kAction.DELETE_TASK)
                tasks.remove(task)
            }
        }
    }

    fun changeTaskName(taskModified: Task, modifiedBy: User, name: String) {
        for (task in tasks) {
            if (task.name == taskModified.name) {
                logChange(task.name, modifiedBy, kAction.CHANGE_TASK_NAME, name)
                task.name = name
            }
        }
    }

    fun changeTaskDescription(taskModified: Task, modifiedBy: User, description: String) {
        for (task in tasks) {
            if (task.name == taskModified.name) {
                task.desc = description
                logChange(task.name, modifiedBy, kAction.CHANGE_TASK_DESCRIPTION)
            }
        }
    }

    fun changeTaskDeadline(taskModified: Task, modifiedBy: User, deadline: String) {
        for (task in tasks) {
            if (task.name == taskModified.name) {
                task.Deadline = deadline
                logChange(task.name, modifiedBy, kAction.CHANGE_TASK_DEADLINE, deadline)
            }
        }
    }

    fun removeDeadline(taskModified: Task, modifiedBy: User) {
        for (task in tasks) {
            if (task.name == taskModified.name) {
                task.Deadline = ""
                task.HasDL = false
                logChange(task.name, modifiedBy, kAction.REMOVE_TASK_DEADLINE)
            }
        }
    }
}