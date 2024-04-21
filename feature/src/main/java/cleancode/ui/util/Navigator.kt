package cleancode.ui.util

import android.app.Activity
import cleancode.ui.activity.*
import cleancode.ui.fragment.MessageCategoryFragment
import cleancode.ui.fragment.MessageDetailsFragment
import cleancode.ui.fragment.UserListFragment
import com.nygar.feature.R

object Navigator {

    /**
     * Goes to main screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    fun navigateToMain(context: Activity) {
        //val intentToLaunch = MainActivity.getCallingIntent(context)
        //context.startActivity(intentToLaunch)
        context.finish()
    }

    /**
     * Goes to the user list screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    fun navigateToUserList(context: Activity) {
        //context.replaceFragmentBack(R.id.content_frame, UserListFragment())
    }

    /**
     * Goes to the user details screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    fun navigateToUserDetails(context: Activity, userId: Int) {
        val intentToLaunch = UserActivity.getCallingIntent(context, userId)
        context.startActivity(intentToLaunch)
    }

    /**
     * Goes to the message category list screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    fun navigateToMessageCategoryList(context: Activity) {
        //context.replaceFragment(R.id.content_frame, MessageCategoryFragment())
    }

    /**
     * Goes to the message list screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    fun navigateToMessageList(context: Activity, categoryId: Int) {
        val intentToLaunch = MessageActivity.getCallingIntent(context, categoryId)
        context.startActivity(intentToLaunch)
    }

    /**
     * Goes to the message details screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    fun navigateToMessageDetails(context: Activity, messageId: Int) {
        val fragment = MessageDetailsFragment.newInstance(messageId)
        //context.replaceFragmentBack(R.id.content_frame, fragment)
    }
}