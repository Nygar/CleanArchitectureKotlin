package cleancode.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cleancode.ui.navigation.ARGUMENT_CHARACTER_DETAILS_ID
import cleancode.ui.navigation.NavItem
import cleancode.ui.view.activity.LoginActivityCompose
import cleancode.ui.view.activity.LoginActivityDelegate
import com.nygar.common.ConstantsTesting.TEST_TAG_NAVIGATION_HOST

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        modifier = Modifier.testTag(TEST_TAG_NAVIGATION_HOST),
        navController = navController,
        startDestination = NavItem.LoginScreen.route
    ) {
        composable(
            route = NavItem.LoginScreen.route
        ) {
            LoginActivityCompose(
                object : LoginActivityDelegate {
                    override fun normalLoginAction() {

                    }

                    override fun googleLoginAction() {

                    }
                }
            )
        }
        composable(
            route = NavItem.CharacterDetail.route,
            arguments = NavItem.CharacterDetail.navArgs
        ) { backStackEntry ->
            val characterId = backStackEntry.arguments?.getInt(ARGUMENT_CHARACTER_DETAILS_ID)
            requireNotNull(characterId)
            /*
            CharacterDetailsScreen(
                characterId = characterId
            ){
                navController.navigateUp()
            }
             */
        }
    }
}
