package cleancode.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import cleancode.ui.view.MessageDetailsScreen
import cleancode.ui.view.MessageListScreen

@Composable
fun MessageNavigation(
    categoryId: Int,
    onBackNavigation: () -> Unit,
) {
    val navController = rememberNavController()

    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = NavDestination.MessageList(categoryId),
    ) {
        composable<NavDestination.MessageList> { backStackEntry ->
            val router = backStackEntry.toRoute<NavDestination.MessageList>()
            MessageListScreen(
                categoryId = router.categoryId,
                onNavigateToMessageDetails = {
                    navController.navigate(NavDestination.MessageDetail(it))
                },
                onNavigateBack = {
                    onBackNavigation.invoke()
                },
            )
        }

        composable<NavDestination.MessageDetail> { backStackEntry ->
            val router = backStackEntry.toRoute<NavDestination.MessageDetail>()
            MessageDetailsScreen(
                messageId = router.messageId,
            ) {
                navController.navigateUp()
            }
        }
    }
}
