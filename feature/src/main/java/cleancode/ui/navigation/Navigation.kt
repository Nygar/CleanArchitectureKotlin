package cleancode.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cleancode.ui.view.CategoryListScreen
import cleancode.ui.view.LoginActivityDelegate
import cleancode.ui.view.LoginScreen
import cleancode.ui.view.UserListScreen
import cleancode.viewmodel.UserLoggedViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import cleancode.ui.view.UserDetailsScreen
import com.nygar.common.ConstantsTesting.TEST_TAG_NAVIGATION_HOST
import com.nygar.designsystem.components.NavigationDrawerView

@Composable
fun Navigation(
    viewModel: UserLoggedViewModel = hiltViewModel(),
) {
    val userLogged = viewModel.userLoggedSingle
    val navController = rememberNavController()
    NavHost(
        modifier = Modifier.testTag(TEST_TAG_NAVIGATION_HOST),
        navController = navController,
        startDestination = NavItem.LoginScreen.route
    ) {
        composable(
            route = NavItem.LoginScreen.route
        ) {
            LoginScreen(
                object : LoginActivityDelegate {
                    override fun normalLoginAction() {
                        navController.navigate(ROUTER_MAIN)
                    }

                    override fun googleLoginAction() {
                        navController.navigate(ROUTER_MAIN)
                    }
                }
            )
        }
        composable(
            route = NavItem.MainScreen.route
        ) {
            NavigationDrawerView(
                fullName = userLogged?.fullName ?: "",
                avatarUrl = userLogged?.avatarUrl ?: "",
                navigateToCategoryList = { CategoryListScreen() },
                navigateToUserList = { UserListScreen(){
                    navController.navigate(NavItem.UserDetail.createNavRoute(it))
                } },
            )
        }
        composable(
            route = NavItem.UserDetail.route,
            arguments = NavItem.UserDetail.navArgs
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getInt(ARGUMENT_USER_DETAILS_ID)
            requireNotNull(userId)
            UserDetailsScreen(
                userId = userId
            ){
                navController.navigateUp()
            }
        }
    }
}
