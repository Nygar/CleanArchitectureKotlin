package cleancode.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class NavItem(
    val baseRoute: String,
    val navArgs: List<NamedNavArgument> = emptyList(),
) {
    val route =
        run {
            val argKeys = navArgs.map { "{${it.name}}" }
            listOf(baseRoute)
                .plus(argKeys)
                .joinToString("/")
        }

    data object LoginScreen : NavItem(ROUTER_LOGIN)

    data object MainScreen : NavItem(ROUTER_MAIN)

    data object MessageList : NavItem(
        ROUTER_MESSAGE_LIST,
        listOf(
            navArgument(
                ARGUMENT_MESSAGE_LIST_ID,
            ) { type = NavType.IntType },
        ),
    ) {
        fun createNavRoute(categoryId: Int) = "$baseRoute/$categoryId"
    }

    data object UserDetail : NavItem(
        ROUTER_USER_DETAILS,
        listOf(
            navArgument(
                ARGUMENT_USER_DETAILS_ID,
            ) { type = NavType.IntType },
        ),
    ) {
        fun createNavRoute(userId: Int) = "$baseRoute/$userId"
    }

    data object MessageDetail : NavItem(
        ROUTER_MESSAGE_DETAILS,
        listOf(
            navArgument(
                ARGUMENT_MESSAGE_DETAILS_ID,
            ) { type = NavType.IntType },
        ),
    ) {
        fun createNavRoute(messageId: Int) = "$baseRoute/$messageId"
    }
}

const val ROUTER_LOGIN = "loginScreen"
const val ROUTER_MAIN = "mainScreen"
const val ROUTER_USER_DETAILS = "userDetailsScreen"
const val ARGUMENT_USER_DETAILS_ID = "userDetailsId"
const val ROUTER_MESSAGE_LIST = "messageListScreen"
const val ARGUMENT_MESSAGE_LIST_ID = "messageListId"
const val ROUTER_MESSAGE_DETAILS = "messageDetailsScreen"
const val ARGUMENT_MESSAGE_DETAILS_ID = "messageDetailsId"
