package com.dirion.walltechtodo.view.global

enum class StatusTask(val statusTitle: String) {
    TO_DO("To Do"),
    IN_PROGRESS("In Progress"),
    UNDER_REVIEW("Under Review"),
    TESTING("Testing"),
    DONE("Done");

    companion object {
        fun convertToStatus(status: String): StatusTask {
           return when(status.lowercase().trimMargin()) {
               "to do" -> TO_DO
               "in progress" -> IN_PROGRESS
               "under review" -> UNDER_REVIEW
               "testing" -> TESTING
               "done" -> DONE

               else -> TO_DO
            }
        }
    }
}