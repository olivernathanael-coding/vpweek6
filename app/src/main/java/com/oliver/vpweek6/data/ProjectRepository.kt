package com.oliver.vpweek6.data

import com.oliver.vpweek6.model.Project
import com.oliver.vpweek6.model.ProjectStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID

object ProjectRepository {

    private val initialProjects = listOf(
        Project(
            id = "proj_1",
            projectCode = "ID: proj-gdsc-01",
            communityId = "gdsc",
            title = "Smart Campus Navigation",
            description = "Sistem navigasi peta gedung kampus interaktif berbasis Jetpack Compose dan Google Maps SDK. Memfasilitasi mahasiswa dan dosen menemukan ruang perkuliahan, laboratorium komputer, perpustakaan, serta fasilitas umum kampus secara akurat disertai rute tercepat dan visualisasi lokasi real-time.",
            techStack = listOf("Kotlin", "Jetpack Compose", "Room", "Google Maps SDK"),
            status = ProjectStatus.IN_PROGRESS
        ),
        Project(
            id = "proj_2",
            projectCode = "ID: proj-gdsc-02",
            communityId = "gdsc",
            title = "Student Attendance QR Scanner",
            description = "Pemindai barcode & QR presensi kehadiran workshop berbasis CameraX & Firebase.",
            techStack = listOf("Jetpack Compose", "Firebase", "CameraX"),
            status = ProjectStatus.COMPLETED
        ),
        Project(
            id = "proj_3",
            projectCode = "ID: proj-gdsc-03",
            communityId = "gdsc",
            title = "Developer RSVP Portal",
            description = "Portal pendaftaran webinar dan bootcamp teknologi sinkronisasi Ktor API server.",
            techStack = listOf("Kotlin", "Ktor", "Compose"),
            status = ProjectStatus.IN_PROGRESS
        ),
        Project(
            id = "proj_4",
            projectCode = "ID: proj-cyber-01",
            communityId = "cybersec",
            title = "Network Sentinel Inspector",
            description = "Monitoring lalu lintas jaringan lokal dan deteksi anomali paket data kampus.",
            techStack = listOf("Python", "Ktor", "Room"),
            status = ProjectStatus.COMPLETED
        )
    )

    private val _projects = MutableStateFlow(initialProjects)
    val projects: StateFlow<List<Project>> = _projects.asStateFlow()

    fun addProject(communityId: String, title: String, description: String, techStack: List<String>) {
        val count = _projects.value.count { it.communityId == communityId } + 1
        val codeNumber = String.format("%02d", count)
        val newProject = Project(
            id = UUID.randomUUID().toString(),
            projectCode = "ID: proj-$communityId-$codeNumber",
            communityId = communityId,
            title = title,
            description = description,
            techStack = techStack,
            status = ProjectStatus.IN_PROGRESS
        )
        _projects.update { it + newProject }
    }

    fun deleteProject(projectId: String) {
        _projects.update { list -> list.filterNot { it.id == projectId } }
    }

    fun toggleProjectStatus(projectId: String) {
        _projects.update { list ->
            list.map { project ->
                if (project.id == projectId) {
                    val newStatus = if (project.status == ProjectStatus.IN_PROGRESS) {
                        ProjectStatus.COMPLETED
                    } else {
                        ProjectStatus.IN_PROGRESS
                    }
                    project.copy(status = newStatus)
                } else {
                    project
                }
            }
        }
    }
}
