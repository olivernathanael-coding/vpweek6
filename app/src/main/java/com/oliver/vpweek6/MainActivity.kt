package com.oliver.vpweek6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.oliver.vpweek6.navigation.AppNavHost
import com.oliver.vpweek6.navigation.CommunitiesRoute
import com.oliver.vpweek6.navigation.DashboardRoute
import com.oliver.vpweek6.ui.theme.Vpweek6Theme
import com.oliver.vpweek6.viewmodel.CampusViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Vpweek6Theme {
                CampusApp()
            }
        }
    }
}

data class TopLevelDestination<T : Any>(
    val route: T,
    val label: String,
    val icon: ImageVector
)

@Composable
fun CampusApp() {
    val navController = rememberNavController()
    val viewModel: CampusViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    val topLevelDestinations = listOf(
        TopLevelDestination(
            route = DashboardRoute,
            label = "Dashboard",
            icon = Icons.Default.Home
        ),
        TopLevelDestination(
            route = CommunitiesRoute,
            label = "Communities",
            icon = Icons.Default.People
        )
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val isTopLevelRoute = topLevelDestinations.any { destination ->
        currentDestination?.hierarchy?.any { it.hasRoute(destination.route::class) } == true
    }

    LaunchedEffect(uiState.userMessage) {
        uiState.userMessage?.let { message ->
            snackbarHostState.showSnackbar(message)
            viewModel.clearUserMessage()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            ) { data ->
                Snackbar(
                    shape = RoundedCornerShape(16.dp),
                    containerColor = Color(0xFF1E293B),
                    contentColor = Color.White,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFF59E0B)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Warning,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = data.visuals.message,
                                color = Color.White,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }

                        TextButton(onClick = { data.dismiss() }) {
                            Text(
                                text = "OK",
                                color = Color(0xFF60A5FA),
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp
                            )
                        }
                    }
                }
            }
        },
        bottomBar = {
            if (isTopLevelRoute) {
                NavigationBar(
                    containerColor = Color.White,
                    tonalElevation = 8.dp
                ) {
                    topLevelDestinations.forEach { destination ->
                        val isSelected = currentDestination?.hierarchy?.any {
                            it.hasRoute(destination.route::class)
                        } == true

                        NavigationBarItem(
                            selected = isSelected,
                            onClick = {
                                navController.navigate(destination.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = destination.icon,
                                    contentDescription = destination.label
                                )
                            },
                            label = {
                                Text(
                                    text = destination.label,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color(0xFF1967D2),
                                selectedTextColor = Color(0xFF1967D2),
                                indicatorColor = Color(0xFFE8F0FE),
                                unselectedIconColor = Color(0xFF64748B),
                                unselectedTextColor = Color(0xFF64748B)
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        AppNavHost(
            navController = navController,
            viewModel = viewModel,
            onUnjoinedCommunityClick = {
                coroutineScope.launch {
                    snackbarHostState.showSnackbar("Harap bergabung ke komunitas terlebih dahulu!")
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}
