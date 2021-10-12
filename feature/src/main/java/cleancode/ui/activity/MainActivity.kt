package cleancode.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import cleancode.model.CategoryModel
import cleancode.model.UserModel
import cleancode.ui.base.BaseActivity
import cleancode.ui.fragment.MessageCategoryFragment
import cleancode.ui.fragment.UserListFragment
import cleancode.ui.fragment.UserLoggedFragment
import com.nygar.feature.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_layout_main.*

/**
 * Activity with navigation drawer
 */
@AndroidEntryPoint
class MainActivity: BaseActivity(), MessageCategoryFragment.MessageCategoryView, UserListFragment.UserListView {
    companion object {
        fun getCallingIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_main)
        initializeActivity(savedInstanceState)
    }

    /**
     * Initializes this activity.
     */
    private fun initializeActivity(savedInstanceState: Bundle?) {

        //Listener can be replace with a lambda
        navigation_view_menu.setNavigationItemSelectedListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.action_messages -> navigator.navigateToMessageCategoryList(this)
                R.id.action_users -> navigator.navigateToUserList(this)
            }
            drawer_layout.closeDrawers()
            true
        }

        val toolbar: Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        val drawerToggle =
            object : ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.drawer_open, R.string.drawer_close) {
                override fun onDrawerClosed(view: View) {
                    super.onDrawerClosed(view)
                    invalidateOptionsMenu()
                    syncState()
                }

                override fun onDrawerOpened(drawerView: View) {
                    super.onDrawerOpened(drawerView)
                    invalidateOptionsMenu()
                    syncState()
                }
            }

        drawer_layout!!.addDrawerListener(drawerToggle)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        drawerToggle.syncState()
        if (savedInstanceState == null) {
            addFragment(R.id.content_frame, MessageCategoryFragment())
        }
        val headerContainer = navigation_view_menu.inflateHeaderView(R.layout.header_layout)
        headerContainer.addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.header_frame, UserLoggedFragment())
                    .commit()
            }

            override fun onViewDetachedFromWindow(v: View) {

            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // The action bar home/up action should open or close the drawer.
        when (item.itemId) {
            android.R.id.home -> {
                drawer_layout.openDrawer(GravityCompat.START)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCategoryClicked(category: CategoryModel) {
        this.navigator.navigateToMessageList(this, category.categoryId)
    }

    override fun onUserClicked(userModel: UserModel) {
        this.navigator.navigateToUserDetails(this, userModel.userId)
    }
}