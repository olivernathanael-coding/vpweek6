package com.oliver.vpweek6.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material.icons.filled.ViewQuilt
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oliver.vpweek6.model.Community
import com.oliver.vpweek6.model.Project
import com.oliver.vpweek6.model.ProjectStatus

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun ProjectDetailScreen(
    project: Project?,
    community: Community?,
    onBackClick: () -> Unit,
    onToggleStatus: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.background(Color(0xFFF8F9FA)),
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White),
                title = {
                    Text(
                        text = "Project Detail",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E293B),
                        fontSize = 20.sp
                    )
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
        }
    ) { innerPadding ->
        if (project == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Project not found.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Red
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFF8F9FA))
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Card(
                    shape = RoundedCornerShape(24.dp),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF0F1C36)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                    ) {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = Color(0xFF1D4ED8)
                        ) {
                            Text(
                                text = community?.name ?: "GDSC",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = project.title,
                            style = MaterialTheme.typography.headlineMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 22.sp,
                            lineHeight = 28.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, Color(0xFFEFF2F6)),
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
                                text = "Mark as Done",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = Color(0xFF1E293B)
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Status: ",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color(0xFF64748B),
                                    fontSize = 12.sp
                                )
                                Spacer(modifier = Modifier.width(4.dp))
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
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
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
                                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                                        )
                                    }
                                }
                            }
                        }

                        Switch(
                            checked = project.status == ProjectStatus.COMPLETED,
                            onCheckedChange = { onToggleStatus(project.id) },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = Color(0xFF1967D2),
                                uncheckedThumbColor = Color.White,
                                uncheckedTrackColor = Color(0xFFCBD5E1)
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, Color(0xFFEFF2F6)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Deskripsi Lengkap Proyek",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Color(0xFF1E293B)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = project.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFF475569),
                            fontSize = 13.sp,
                            lineHeight = 20.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    border = BorderStroke(1.dp, Color(0xFFEFF2F6)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Tech Stack Terpilih",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Color(0xFF1E293B)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            project.techStack.forEach { tech ->
                                val (icon, iconTint) = getTechStackIcon(tech)
                                Surface(
                                    shape = RoundedCornerShape(12.dp),
                                    color = Color(0xFFEBF3FE),
                                    border = BorderStroke(1.dp, Color(0xFFD2E3FC))
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = icon,
                                            contentDescription = null,
                                            tint = iconTint,
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = tech,
                                            color = Color(0xFF1E293B),
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 13.sp
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

fun getTechStackIcon(tech: String): Pair<ImageVector, Color> {
    return when {
        tech.contains("Maps", ignoreCase = true) -> Pair(Icons.Default.LocationOn, Color(0xFFEA4335))
        tech.contains("Room", ignoreCase = true) || tech.contains("Firebase", ignoreCase = true) -> Pair(Icons.Default.Storage, Color(0xFF1967D2))
        tech.contains("Compose", ignoreCase = true) -> Pair(Icons.Default.ViewQuilt, Color(0xFF4285F4))
        else -> Pair(Icons.Default.Code, Color(0xFF1967D2))
    }
}
