package com.oliver.vpweek6.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Layers
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhoneIphone
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oliver.vpweek6.model.Community
import com.oliver.vpweek6.model.Project
import com.oliver.vpweek6.model.StudentProfile
import com.oliver.vpweek6.viewmodel.CampusUiState

@Composable
fun DashboardScreen(
    uiState: CampusUiState,
    modifier: Modifier = Modifier
) {
    val student = uiState.studentProfile
    val joinedClubs = uiState.communities.filter { it.isJoined }
    val joinedClubsCount = joinedClubs.size
    val totalProjectsCount = uiState.projects.size

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFF8F9FA))
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "CampusTech Hub",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E293B),
                        fontSize = 20.sp
                    )
                    Text(
                        text = "Student Profile & Portfolio Dashboard",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF64748B),
                        fontSize = 12.sp
                    )
                }

                Box(
                    modifier = Modifier
                        .size(36.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF4338CA)),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = student.initials,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }
            }
        }

        item {
            StudentGradientCard(student = student)
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                DashboardStatCard(
                    title = "Joined Clubs",
                    count = joinedClubsCount,
                    subtitle = "Komunitas yang Diikuti",
                    icon = Icons.Default.Person,
                    iconBg = Color(0xFFEBF3FE),
                    iconTint = Color(0xFF1967D2),
                    modifier = Modifier.weight(1f)
                )
                DashboardStatCard(
                    title = "Total Projects",
                    count = totalProjectsCount,
                    subtitle = "Seluruh Proyek Dibuat",
                    icon = Icons.Default.Layers,
                    iconBg = Color(0xFFF3E8FF),
                    iconTint = Color(0xFF7C3AED),
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = Color(0xFF1967D2),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Semua Komunitas yang Diikuti ($joinedClubsCount)",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Color(0xFF1E293B)
                    )
                }
                Text(
                    text = "Status: Joined",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color(0xFF1967D2),
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        if (joinedClubsCount == 0) {
            item {
                FallbackEmptyCard(message = "Belum bergabung dengan komunitas")
            }
        } else {
            items(joinedClubs) { community ->
                DashboardCommunityCard(community = community)
            }
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.CheckBoxOutlineBlank,
                        contentDescription = null,
                        tint = Color(0xFF7C3AED),
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Semua Proyek Mahasiswa ($totalProjectsCount)",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Color(0xFF1E293B)
                    )
                }
                Text(
                    text = "Total: $totalProjectsCount Proyek",
                    style = MaterialTheme.typography.labelMedium,
                    color = Color(0xFF1967D2),
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        if (totalProjectsCount == 0) {
            item {
                FallbackEmptyCard(message = "Belum ada proyek yang dicatat")
            }
        } else {
            items(uiState.projects) { project ->
                val originCommunity = uiState.communities.find { it.id == project.communityId }
                DashboardProjectCard(
                    project = project,
                    communityName = originCommunity?.name ?: "Community"
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun StudentGradientCard(student: StudentProfile) {
    Card(
        shape = RoundedCornerShape(24.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF0F3BB6),
                            Color(0xFF2865EC)
                        )
                    )
                )
                .padding(20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(CircleShape)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = student.initials,
                        color = Color(0xFF0F3BB6),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0x33FFFFFF),
                        modifier = Modifier.padding(bottom = 4.dp)
                    ) {
                        Text(
                            text = student.statusTag,
                            color = Color.White,
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            fontSize = 10.sp,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp)
                        )
                    }

                    Text(
                        text = student.name,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 20.sp
                    )

                    Text(
                        text = student.majorAndBatch,
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White.copy(alpha = 0.9f),
                        fontSize = 13.sp
                    )

                    Text(
                        text = student.nim,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.White.copy(alpha = 0.75f),
                        fontSize = 12.sp
                    )
                }
            }
        }
    }
}

@Composable
fun DashboardStatCard(
    title: String,
    count: Int,
    subtitle: String,
    icon: ImageVector,
    iconBg: Color,
    iconTint: Color,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFEFF2F6)),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(iconBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = iconTint,
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "$count",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 26.sp,
                color = Color(0xFF1E293B)
            )

            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                color = Color(0xFF1E293B)
            )

            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF64748B),
                fontSize = 11.sp
            )
        }
    }
}

@Composable
fun DashboardCommunityCard(community: Community) {
    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFEFF2F6)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                val (bg, tint, icon) = getCommunityIconStyle(community.iconType)
                Box(
                    modifier = Modifier
                        .size(44.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(bg),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = tint,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = community.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = Color(0xFF1E293B)
                    )
                    Text(
                        text = "${community.category} • ${community.projectsCount} Proyek",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF64748B),
                        fontSize = 12.sp
                    )
                }
            }

            Surface(
                shape = RoundedCornerShape(20.dp),
                color = Color(0xFFFCE8E6),
                border = BorderStroke(1.dp, Color(0xFFF8C4C0))
            ) {
                Text(
                    text = "Joined ✓",
                    color = Color(0xFFD93025),
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }
        }
    }
}

@Composable
fun DashboardProjectCard(project: Project, communityName: String) {
    Card(
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFEFF2F6)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFFEBF3FE)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Android,
                    contentDescription = null,
                    tint = Color(0xFF1967D2),
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = communityName,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF1967D2),
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp
                )
                Text(
                    text = project.title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color(0xFF1E293B)
                )
                Text(
                    text = project.techStack.joinToString(" • "),
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFF64748B),
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Composable
fun FallbackEmptyCard(message: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFEFF2F6)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF94A3B8),
                fontWeight = FontWeight.Medium
            )
        }
    }
}

fun getCommunityIconStyle(iconType: String): Triple<Color, Color, ImageVector> {
    return when (iconType) {
        "shield" -> Triple(Color(0xFFFCE8E6), Color(0xFFD93025), Icons.Default.Security)
        "phone" -> Triple(Color(0xFFE0F2FE), Color(0xFF0284C7), Icons.Default.PhoneIphone)
        "sparkles" -> Triple(Color(0xFFF3E8FF), Color(0xFF7C3AED), Icons.Default.Star)
        else -> Triple(Color(0xFFEBF3FE), Color(0xFF1967D2), Icons.Default.Code)
    }
}
