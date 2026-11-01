package com.oliver.vpweek6.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.oliver.vpweek6.ui.screen.ClubProjectsScreen
import com.oliver.vpweek6.ui.screen.CommunitiesScreen
import com.oliver.vpweek6.ui.screen.DashboardScreen
import com.oliver.vpweek6.ui.screen.ProjectDetailScreen
import com.oliver.vpweek6.viewmodel.CampusViewModel

@Composable
fun AppNavHost(
    navController: NavHostController,
    viewModel: CampusViewModel,
    onUnjoinedCommunityClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = DashboardRoute,
        modifier = modifier
    ) {
        composable<DashboardRoute> {
            DashboardScreen(
                uiState = uiState
            )
        }

        composable<CommunitiesRoute> {
            CommunitiesScreen(
                uiState = uiState,
                onToggleJoin = { communityId -> viewModel.toggleCommunityJoin(communityId) },
                onNavigateToClubProjects = { communityId ->
                    navController.navigate(ClubProjectsRoute(communityId = communityId))
                },
                onUnjoinedCommunityClick = onUnjoinedCommunityClick
            )
        }

        composable<ClubProjectsRoute> { backStackEntry ->
            val route: ClubProjectsRoute = backStackEntry.toRoute()
            val communityId = route.communityId
            val community = uiState.communities.find { it.id == communityId }
            val communityProjects = uiState.projects.filter { it.communityId == communityId }

            ClubProjectsScreen(
                community = community,
                projects = communityProjects,
                onBackClick = { navController.popBackStack() },
                onProjectClick = { projectId ->
                    navController.navigate(ProjectDetailRoute(projectId = projectId))
                },
                onDeleteProject = { projectId -> viewModel.deleteProject(projectId) },
                onAddProject = { title, description, techStack ->
                    viewModel.addProject(communityId, title, description, techStack)
                }
            )
        }

        composable<ProjectDetailRoute> { backStackEntry ->
            val route: ProjectDetailRoute = backStackEntry.toRoute()
            val projectId = route.projectId
            val project = uiState.projects.find { it.id == projectId }
            val originCommunity = uiState.communities.find { it.id == project?.communityId }

            ProjectDetailScreen(
                project = project,
                community = originCommunity,
                onBackClick = { navController.popBackStack() },
                onToggleStatus = { id -> viewModel.toggleProjectStatus(id) }
            )
        }
    }
}
