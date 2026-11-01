package com.oliver.vpweek6.data

import com.oliver.vpweek6.model.Community
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object CommunityRepository {

    private val initialCommunities = listOf(
        Community(
            id = "gdsc",
            name = "GDSC",
            category = "Google Developer Student Club",
            membersCount = 142,
            projectsCount = 3,
            iconType = "code",
            description = "Fokus pada ekosistem Google: Android Compose, Flutter, Firebase, dan Google Cloud Platform.",
            isJoined = true
        ),
        Community(
            id = "cybersec",
            name = "CyberSecurity Guild",
            category = "Security & Pentesting",
            membersCount = 88,
            projectsCount = 1,
            iconType = "shield",
            description = "Eksplorasi ethical hacking, CTF challenges, reverse engineering, dan pertahanan jaringan kampus.",
            isJoined = true
        ),
        Community(
            id = "ai_soc",
            name = "AI Society",
            category = "Machine Learning & LLM",
            membersCount = 115,
            projectsCount = 0,
            iconType = "sparkles",
            description = "Riset terapan deep learning, computer vision, agen cerdas, dan fine-tuning large language models.",
            isJoined = false
        ),
        Community(
            id = "mobile_dev",
            name = "Mobile Dev Community",
            category = "Native Jetpack Compose & iOS",
            membersCount = 95,
            projectsCount = 1,
            iconType = "phone",
            description = "Pengembangan aplikasi mobile native Android Jetpack Compose dan iOS Swift.",
            isJoined = true
        ),
        Community(
            id = "web_dev",
            name = "Web Dev Guild",
            category = "Modern Web & Backend",
            membersCount = 120,
            projectsCount = 0,
            iconType = "code",
            description = "Pengembangan aplikasi web modern fullstack dan arsitektur microservices.",
            isJoined = false
        ),
        Community(
            id = "game_dev",
            name = "Game Dev Society",
            category = "Unity & Game Mechanics",
            membersCount = 76,
            projectsCount = 0,
            iconType = "game",
            description = "Desain game 2D/3D, rendering grafis, dan pengembangan mekanik game interaktif.",
            isJoined = false
        )
    )

    private val _communities = MutableStateFlow(initialCommunities)
    val communities: StateFlow<List<Community>> = _communities.asStateFlow()

    fun toggleCommunityJoin(communityId: String) {
        _communities.update { list ->
            list.map { community ->
                if (community.id == communityId) {
                    community.copy(isJoined = !community.isJoined)
                } else {
                    community
                }
            }
        }
    }
}
