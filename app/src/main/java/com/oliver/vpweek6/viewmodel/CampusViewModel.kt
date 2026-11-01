package com.oliver.vpweek6.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oliver.vpweek6.data.CommunityRepository
import com.oliver.vpweek6.data.ProjectRepository
import com.oliver.vpweek6.model.StudentProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class CampusViewModel(
    private val communityRepository: CommunityRepository = CommunityRepository,
    private val projectRepository: ProjectRepository = ProjectRepository
) : ViewModel() {

    private val _studentProfile = MutableStateFlow(
        StudentProfile(
            name = "Aditya Pratama",
            majorAndBatch = "Teknik Informatika • Angkatan 2022",
            nim = "NIM: 22001045",
            statusTag = "MAHASISWA AKTIF",
            initials = "AP"
        )
    )

    private val _userMessage = MutableStateFlow<String?>(null)

    val uiState: StateFlow<CampusUiState> = combine(
        _studentProfile,
        communityRepository.communities,
        projectRepository.projects,
        _userMessage
    ) { profile, communities, projects, message ->
        CampusUiState(
            studentProfile = profile,
            communities = communities,
            projects = projects,
            userMessage = message
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = CampusUiState()
    )

    fun toggleCommunityJoin(communityId: String) {
        communityRepository.toggleCommunityJoin(communityId)
    }

    fun addProject(communityId: String, title: String, description: String, techStack: List<String>) {
        projectRepository.addProject(communityId, title, description, techStack)
    }

    fun deleteProject(projectId: String) {
        projectRepository.deleteProject(projectId)
    }

    fun toggleProjectStatus(projectId: String) {
        projectRepository.toggleProjectStatus(projectId)
    }

    fun showUserMessage(message: String) {
        _userMessage.update { message }
    }

    fun clearUserMessage() {
        _userMessage.update { null }
    }
}
