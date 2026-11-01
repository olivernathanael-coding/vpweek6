package com.oliver.vpweek6.ui.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.oliver.vpweek6.model.Community

val AVAILABLE_TECH_STACKS = listOf(
    "Kotlin",
    "Jetpack Compose",
    "Room",
    "Firebase",
    "Ktor",
    "Python",
    "Google Maps SDK"
)

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddProjectDialog(
    community: Community?,
    onDismissRequest: () -> Unit,
    onSaveProject: (title: String, description: String, techStack: List<String>) -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedTechStack by remember { mutableStateOf(setOf<String>()) }

    val isFormValid = title.isNotBlank() && description.isNotBlank() && selectedTechStack.isNotEmpty()
    val communityName = community?.let { "${it.name} (${it.category})" } ?: "General"

    Dialog(onDismissRequest = onDismissRequest) {
        Card(
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Add Project",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )

                Text(
                    text = "Komunitas: $communityName",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Judul Proyek *",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    placeholder = { Text("Contoh: Smart Campus Navigation", color = Color.LightGray) },
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color(0xFFF8F9FA),
                        focusedContainerColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Deskripsi Proyek (Minimal 3 Baris) *",
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.height(4.dp))
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    placeholder = { Text("Tuliskan deskripsi lengkap...", color = Color.LightGray) },
                    minLines = 3,
                    maxLines = 5,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedContainerColor = Color(0xFFF8F9FA),
                        focusedContainerColor = Color.White
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Tech Stack (Pilihan Multi-Selection) *",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                    Text(
                        text = "${selectedTechStack.size} Dipilih",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color(0xFF1967D2),
                        fontWeight = FontWeight.Bold
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AVAILABLE_TECH_STACKS.forEach { tech ->
                        val isSelected = selectedTechStack.contains(tech)
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = if (isSelected) Color(0xFFE8F0FE) else Color(0xFFF8F9FA),
                            border = BorderStroke(
                                width = 1.dp,
                                color = if (isSelected) Color(0xFF1967D2) else Color(0xFFDADCE0)
                            ),
                            modifier = Modifier.clickable {
                                selectedTechStack = if (isSelected) {
                                    selectedTechStack - tech
                                } else {
                                    selectedTechStack + tech
                                }
                            }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = if (isSelected) "✓  " else "+  ",
                                    color = if (isSelected) Color(0xFF1967D2) else Color.Gray,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 12.sp
                                )
                                Text(
                                    text = tech,
                                    color = if (isSelected) Color(0xFF1967D2) else Color(0xFF3C4043),
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    fontSize = 13.sp
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(onClick = onDismissRequest) {
                        Text(
                            text = "Cancel",
                            color = Color(0xFF1967D2),
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Button(
                        onClick = {
                            if (isFormValid) {
                                onSaveProject(title.trim(), description.trim(), selectedTechStack.toList())
                            }
                        },
                        enabled = isFormValid,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF1967D2),
                            disabledContainerColor = Color(0xFFDADCE0)
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = "Save",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 12.dp)
                        )
                    }
                }
            }
        }
    }
}
