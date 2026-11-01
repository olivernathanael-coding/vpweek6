package com.oliver.vpweek6.viewmodel

import com.oliver.vpweek6.model.Community
import com.oliver.vpweek6.model.Project
import com.oliver.vpweek6.model.StudentProfile

data class CampusUiState(
    val studentProfile: StudentProfile = StudentProfile(),
    val communities: List<Community> = emptyList(),
    val projects: List<Project> = emptyList(),
    val userMessage: String? = null
)
