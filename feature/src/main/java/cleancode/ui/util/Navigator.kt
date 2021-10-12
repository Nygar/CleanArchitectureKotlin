package cleancode.ui.util

import android.app.Activity
import cleancode.ui.activity.*
import cleancode.ui.base.BaseActivity
import cleancode.ui.fragment.MessageCategoryFragment
import cleancode.ui.fragment.MessageDetailsFragment
import cleancode.ui.fragment.UserDetailsFragment
import cleancode.ui.fragment.UserListFragment
import com.nygar.feature.R

object Navigator {

    /**
     * Goes to main screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    fun navigateToMain(context: BaseActivity) {
        val intentToLaunch = MainActivity.getCallingIntent(context)
        context.startActivity(intentToLaunch)
        context.finish()
    }

    /**
     * Goes to the user list screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    fun navigateToUserList(context: BaseActivity) {
        context.replaceFragmentBack(R.id.content_frame, UserListFragment())
    }

    /**
     * Goes to the user details screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    fun navigateToUserDetails(context: BaseActivity, userId: Int) {
        val fragment = UserDetailsFragment.newInstance(userId)
        context.replaceFragmentBack(R.id.content_frame, fragment)
    }

    /**
     * Goes to the message category list screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    fun navigateToMessageCategoryList(context: BaseActivity) {
        context.replaceFragment(R.id.content_frame, MessageCategoryFragment())
    }

    /**
     * Goes to the message list screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    fun navigateToMessageList(context: BaseActivity, categoryId: Int) {
        val intentToLaunch = MessageListActivity.getCallingIntent(context, categoryId)
        context.startActivity(intentToLaunch)
    }

    /**
     * Goes to the message details screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    fun navigateToMessageDetails(context: BaseActivity, messageId: Int) {
        val fragment = MessageDetailsFragment.newInstance(messageId)
        context.replaceFragmentBack(R.id.content_frame, fragment)
    }

    /**
     * Goes to the login screen.
     *
     * @param context A Context needed to open the destiny activity.
     */
    fun navigateToLogin(context: Activity) {
        val intentToLaunch = LoginActivity.getCallingIntent(context)
        context.startActivity(intentToLaunch)
        context.finish()
    }
}