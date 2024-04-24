package com.nygar.designsystem.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nygar.designsystem.R
import com.nygar.designsystem.theme.ThemeConfig
import kotlinx.coroutines.launch

const val ROUTER_CATEGORY_LIST = "categoryListScreen"
const val ROUTER_MESSAGE_LIST = "messageListScreen"
const val ROUTER_USER_LIST = "userListScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawerView(
    fullName: String = "",
    avatarUrl: String = "",
    navigateToCategoryList: @Composable () -> Unit,
    navigateToUserList: @Composable () -> Unit,
) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val topAppBarScrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

    Surface(
        modifier =
            Modifier
                .fillMaxSize()
                .nestedScroll(topAppBarScrollBehavior.nestedScrollConnection),
        color = MaterialTheme.colorScheme.background,
    ) {
        ModalNavigationDrawer(
            modifier = Modifier,
            drawerContent = {
                NavigationDrawerLateralContent(
                    modifier = Modifier.fillMaxWidth(0.7f),
                    fullName = fullName,
                    avatarUrl = avatarUrl,
                    navigateToCategory = { navController.navigate(ROUTER_CATEGORY_LIST) },
                    navigateToUser = { navController.navigate(ROUTER_USER_LIST) },
                    closeDrawer = { scope.launch { drawerState.close() } },
                )
            },
            drawerState = drawerState,
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(text = stringResource(id = R.string.app_name))
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            }) {
                                Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                            }
                        },
                        scrollBehavior = topAppBarScrollBehavior,
                    )
                },
            ) { paddingValues ->

                NavHost(
                    navController = navController,
                    startDestination = ROUTER_CATEGORY_LIST,
                    modifier = Modifier.padding(paddingValues),
                ) {
                    composable(ROUTER_CATEGORY_LIST) {
                        navigateToCategoryList()
                    }

                    composable(ROUTER_USER_LIST) {
                        navigateToUserList()
                    }
                }
            }
        }
    }
}

@Composable
fun NavigationDrawerLateralContent(
    modifier: Modifier = Modifier,
    fullName: String = "",
    avatarUrl: String = "",
    navigateToCategory: () -> Unit,
    navigateToUser: () -> Unit,
    closeDrawer: () -> Unit,
) {
    ModalDrawerSheet {
        var selectedRow by rememberSaveable {
            mutableStateOf(ROUTER_CATEGORY_LIST)
        }

        ModalDrawerSheet(modifier = modifier) {
            DrawerHeader(
                modifier = Modifier,
                fullName = fullName,
                avatarUrl = avatarUrl,
            )
            Spacer(modifier = Modifier.padding(ThemeConfig.theme.spacing.sizeSpacing5))
            NavigationDrawerItem(
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                label = {
                    Text(
                        text = stringResource(id = R.string.action_messages),
                        style = MaterialTheme.typography.labelSmall,
                    )
                },
                selected = selectedRow == ROUTER_CATEGORY_LIST,
                onClick = {
                    selectedRow = ROUTER_CATEGORY_LIST
                    navigateToCategory()
                    closeDrawer()
                },
                icon = { Icon(imageVector = Icons.Default.Home, contentDescription = null) },
            )

            NavigationDrawerItem(
                modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                label = {
                    Text(
                        text = stringResource(id = R.string.action_users),
                        style = MaterialTheme.typography.labelSmall,
                    )
                },
                selected = selectedRow == ROUTER_USER_LIST,
                onClick = {
                    selectedRow = ROUTER_USER_LIST
                    navigateToUser()
                    closeDrawer()
                },
                icon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
            )
        }
    }
}
