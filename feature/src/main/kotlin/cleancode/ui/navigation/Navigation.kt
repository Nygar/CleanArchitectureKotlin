package cleancode.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import cleancode.ui.view.CategoryListScreen
import cleancode.ui.view.LoginScreen
import cleancode.ui.view.MessageDetailsScreen
import cleancode.ui.view.MessageListScreen
import cleancode.ui.view.UserDetailsScreen
import cleancode.ui.view.UserListScreen
import cleancode.viewmodel.UserLoggedViewModel
import com.nygar.common.ConstantsTesting.TEST_TAG_NAVIGATION_HOST
import com.nygar.designsystem.components.NavigationDrawerView

@Composable
fun Navigation(viewModel: UserLoggedViewModel = hiltViewModel()) {
    val userLogged = viewModel.userLoggedSingle
    val navController = rememberNavController()

    NavHost(
        modifier = Modifier.testTag(TEST_TAG_NAVIGATION_HOST),
        navController = navController,
        startDestination = NavDestination.Login,
    ) {
        composable<NavDestination.Login> {
            LoginScreen {
                navController.navigate(NavDestination.Main)
                /*
                navController.navigate(NavItem.MainScreen){
                    popUpTo(ROUTER_LOGIN) {
                        inclusive = true
                    }
                }

                 */
            }
        }
        composable<NavDestination.Main> {
            NavigationDrawerView(
                fullName = userLogged?.fullName ?: "",
                avatarUrl = userLogged?.avatarUrl ?: "",
                navigateToCategoryList = {
                    CategoryListScreen {
                        navController.navigate(NavDestination.MessageList(it))
                    }
                },
                navigateToUserList = {
                    UserListScreen {
                        navController.navigate(NavDestination.UserDetail(it))
                    }
                },
            )
        }
        composable<NavDestination.UserDetail> { backStackEntry ->
            /*
            val userId = backStackEntry.arguments?.getInt(ARGUMENT_USER_DETAILS_ID)
            requireNotNull(userId)
             */
            val router = backStackEntry.toRoute<NavDestination.UserDetail>()
            UserDetailsScreen(
                userId = router.userId,
            ) {
                navController.navigateUp()
            }
        }

        composable<NavDestination.MessageList> { backStackEntry ->
            /*
            val categoryId = backStackEntry.arguments?.getInt(ARGUMENT_MESSAGE_LIST_ID)
            requireNotNull(categoryId)
             */
            val router = backStackEntry.toRoute<NavDestination.MessageList>()
            MessageListScreen(
                categoryId = router.categoryId,
                onNavigateToMessageDetails = {
                    navController.navigate(NavDestination.MessageDetail(it))
                },
                onNavigateBack = {
                    navController.navigateUp()
                },
            )
        }

        composable<NavDestination.MessageDetail> { backStackEntry ->
            /*
            val messageId = backStackEntry.arguments?.getInt(ARGUMENT_MESSAGE_DETAILS_ID)
            requireNotNull(messageId)
             */
            val router = backStackEntry.toRoute<NavDestination.MessageDetail>()
            MessageDetailsScreen(
                messageId = router.messageId,
            ) {
                navController.navigateUp()
            }
        }
    }
}
