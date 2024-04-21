package cleancode.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavItem(
    val baseRoute: String,
    val navArgs: List<NamedNavArgument> = emptyList()
) {
    val route = run {
        val argKeys = navArgs.map { "{${it.name}}" }
        listOf(baseRoute)
            .plus(argKeys)
            .joinToString("/")
    }
    data object LoginScreen: NavItem(ROUTER_LOGIN)
    data object CharacterDetail: NavItem(
        ROUTER_USER_DETAILS, listOf(navArgument(
            ARGUMENT_CHARACTER_DETAILS_ID
        ) { type = NavType.IntType })){
        fun createNavRoute(characterId: Int) = "$baseRoute/$characterId"
    }
}

const val ROUTER_LOGIN = "loginScreen"
const val ROUTER_MESSAGE_LIST = "messageListScreen"
const val ROUTER_USER_DETAILS = "userDetailsScreen"
const val ARGUMENT_CHARACTER_DETAILS_ID = "characterId"