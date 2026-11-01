package com.oliver.vpweek6.model

data class Community(
    val id: String,
    val name: String,
    val description: String,
    val category: String,
    val membersCount: Int = 100,
    val projectsCount: Int = 0,
    val iconType: String = "code",
    val isJoined: Boolean = false
)
