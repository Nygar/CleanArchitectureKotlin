package cleancode.ui.navigation

import kotlinx.serialization.Serializable

sealed interface NavDestination {
    @Serializable
    object Login

    @Serializable
    object Main

    @Serializable
    data class MessageList(val categoryId: Int)

    @Serializable
    data class UserDetail(val userId: Int)

    @Serializable
    data class MessageDetail(val messageId: Int)
}
