package com.oliver.vpweek6.model

enum class ProjectStatus(val label: String) {
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed")
}

data class Project(
    val id: String,
    val projectCode: String = "proj-01",
    val communityId: String,
    val title: String,
    val description: String,
    val techStack: List<String>,
    val status: ProjectStatus = ProjectStatus.IN_PROGRESS
)
