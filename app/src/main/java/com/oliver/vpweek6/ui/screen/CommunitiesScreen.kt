package com.oliver.vpweek6.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oliver.vpweek6.model.Community
import com.oliver.vpweek6.viewmodel.CampusUiState

@Composable
fun CommunitiesScreen(
    uiState: CampusUiState,
    onToggleJoin: (String) -> Unit,
    onNavigateToClubProjects: (String) -> Unit,
    onUnjoinedCommunityClick: () -> Unit,
    modifier: Modifier = Modifier
) {
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
                        text = "Communities Directory",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E293B),
                        fontSize = 20.sp
                    )
                    Text(
                        text = "Pilih komunitas untuk membuka Club Projects",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF64748B),
                        fontSize = 12.sp
                    )
                }

                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color(0xFF1E293B)
                    )
                }
            }
        }

        items(uiState.communities) { community ->
            CommunityDirectoryCard(
                community = community,
                onToggleJoin = { onToggleJoin(community.id) },
                onCardClick = {
                    if (community.isJoined) {
                        onNavigateToClubProjects(community.id)
                    } else {
                        onUnjoinedCommunityClick()
                    }
                }
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun CommunityDirectoryCard(
    community: Community,
    onToggleJoin: () -> Unit,
    onCardClick: () -> Unit
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
                verticalAlignment = Alignment.CenterVertically
            ) {
                val (bg, tint, icon) = getCommunityIconStyle(community.iconType)
                Box(
                    modifier = Modifier
                        .size(46.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(bg),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = tint,
                        modifier = Modifier.size(24.dp)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = community.name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color(0xFF1E293B)
                    )
                    Text(
                        text = "${community.category} • ${community.membersCount} Mahasiswa",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF64748B),
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = community.description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF475569),
                fontSize = 13.sp,
                lineHeight = 18.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (community.isJoined) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { onCardClick() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.LockOpen,
                            contentDescription = null,
                            tint = Color(0xFF1967D2),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Unlocked • Buka Proyek",
                            color = Color(0xFF1967D2),
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = null,
                            tint = Color(0xFF1967D2),
                            modifier = Modifier.size(14.dp)
                        )
                    }
                } else {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            tint = Color(0xFF94A3B8),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Terkunci (Gate Akses)",
                            color = Color(0xFF94A3B8),
                            fontWeight = FontWeight.Medium,
                            fontSize = 13.sp
                        )
                    }
                }

                if (community.isJoined) {
                    OutlinedButton(
                        onClick = onToggleJoin,
                        shape = RoundedCornerShape(20.dp),
                        border = BorderStroke(1.dp, Color(0xFFF8C4C0)),
                        colors = ButtonDefaults.outlinedButtonColors(
                            containerColor = Color(0xFFFCE8E6),
                            contentColor = Color(0xFFD93025)
                        )
                    ) {
                        Text(
                            text = "✓  Joined",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }
                } else {
                    Button(
                        onClick = onToggleJoin,
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF1967D2),
                            contentColor = Color.White
                        )
                    ) {
                        Text(
                            text = "+ Join",
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.sp
                        )
                    }
                }
            }
        }
    }
}
