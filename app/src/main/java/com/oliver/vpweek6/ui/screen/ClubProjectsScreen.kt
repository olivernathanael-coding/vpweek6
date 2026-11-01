package com.oliver.vpweek6.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.CheckBoxOutlineBlank
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oliver.vpweek6.model.Community
import com.oliver.vpweek6.model.Project
import com.oliver.vpweek6.model.ProjectStatus
import com.oliver.vpweek6.ui.widgets.AddProjectDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ClubProjectsScreen(
    community: Community?,
    projects: List<Project>,
    onBackClick: () -> Unit,
    onProjectClick: (String) -> Unit,
    onDeleteProject: (String) -> Unit,
    onAddProject: (title: String, description: String, techStack: List<String>) -> Unit,
    modifier: Modifier = Modifier
) {
    var showAddDialog by remember { mutableStateOf(false) }
    val communityName = community?.name ?: "Club"

    Scaffold(
        modifier = modifier.background(Color(0xFFF8F9FA)),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
                title = {
                    Column {
                        Text(
                            text = "$communityName Projects",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1E293B),
                            fontSize = 18.sp
                        )
                        Text(
                            text = community?.category ?: "Google Developer Student Club",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF64748B),
                            fontSize = 11.sp
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color(0xFF1E293B)
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { showAddDialog = true },
                containerColor = Color(0xFFE8F0FE),
                contentColor = Color(0xFF1967D2),
                shape = RoundedCornerShape(16.dp),
                icon = { Icon(Icons.Default.Add, contentDescription = null) },
                text = { Text("Add Project", fontWeight = FontWeight.Bold) }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8F9FA))
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(12.dp))
                    Card(
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F0FE)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = community?.category ?: "Google Developer Student Club",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF1967D2),
                                    fontSize = 16.sp
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "${community?.membersCount ?: 142} Anggota • ${projects.size} Proyek Aktif",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color(0xFF1967D2).copy(alpha = 0.8f),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium
                                )
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
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                                )
                            }
                        }
                    }
                }

                item {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.CheckBoxOutlineBlank,
                            contentDescription = null,
                            tint = Color(0xFF7C3AED),
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Daftar Proyek Mahasiswa (${projects.size})",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Color(0xFF1E293B)
                        )
                    }
                }

                if (projects.isEmpty()) {
                    item {
                        Card(
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White),
                            border = BorderStroke(1.dp, Color(0xFFEFF2F6)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(24.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Belum ada proyek di komunitas ini. Klik + Add Project untuk menambahkan!",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color(0xFF94A3B8),
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                } else {
                    items(projects, key = { it.id }) { project ->
                        ProjectItemCard(
                            project = project,
                            onCardClick = { onProjectClick(project.id) },
                            onDeleteClick = { onDeleteProject(project.id) }
                        )
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(80.dp))
                }
            }
        }

        if (showAddDialog) {
            AddProjectDialog(
                community = community,
                onDismissRequest = { showAddDialog = false },
                onSaveProject = { title, description, techStack ->
                    onAddProject(title, description, techStack)
                    showAddDialog = false
                }
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProjectItemCard(
    project: Project,
    onCardClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, Color(0xFFEFF2F6)),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCardClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    val (bg, tint, icon) = getProjectIconStyle(project.id)
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
                            text = project.title,
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Color(0xFF1E293B)
                        )
                        Text(
                            text = project.description,
                            style = MaterialTheme.typography.bodySmall,
                            color = Color(0xFF64748B),
                            fontSize = 12.sp,
                            maxLines = 2,
                            lineHeight = 16.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.width(8.dp))

                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFFCE8E6))
                        .clickable { onDeleteClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Remove,
                        contentDescription = "Delete Project",
                        tint = Color(0xFFD93025),
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                project.techStack.forEach { tech ->
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color(0xFFF1F5F9)
                    ) {
                        Text(
                            text = tech,
                            color = Color(0xFF475569),
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 11.sp,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = project.projectCode,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFF94A3B8),
                    fontSize = 11.sp
                )

                if (project.status == ProjectStatus.COMPLETED) {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFFD1FAE5)
                    ) {
                        Text(
                            text = "• Completed",
                            color = Color(0xFF059669),
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                } else {
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFFFEF3C7)
                    ) {
                        Text(
                            text = "• In Progress",
                            color = Color(0xFFD97706),
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

fun getProjectIconStyle(projectId: String): Triple<Color, Color, ImageVector> {
    return when {
        projectId.contains("2") -> Triple(Color(0xFFD1FAE5), Color(0xFF059669), Icons.Default.Code)
        projectId.contains("3") -> Triple(Color(0xFFF3E8FF), Color(0xFF7C3AED), Icons.Default.Cloud)
        else -> Triple(Color(0xFFEBF3FE), Color(0xFF1967D2), Icons.Default.Android)
    }
}
