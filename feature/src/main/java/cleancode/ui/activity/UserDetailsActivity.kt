package cleancode.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import cleancode.ui.base.BaseActivity
import cleancode.ui.fragment.UserDetailsFragment
import com.nygar.feature.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.toolbar_main.*

/**
 * Activity that shows details of a certain user.
 */
@AndroidEntryPoint
class UserDetailsActivity : BaseActivity() {

    companion object {

        private const val INTENT_EXTRA_PARAM_USER_ID = "com.nygar.INTENT_PARAM_USER_ID"
        private const val INSTANCE_STATE_PARAM_USER_ID = "com.nygar.STATE_PARAM_USER_ID"

        fun getCallingIntent(context: Context, userId: Int): Intent {
            val callingIntent = Intent(context, UserDetailsActivity::class.java)
            callingIntent.putExtra(INTENT_EXTRA_PARAM_USER_ID, userId)
            return callingIntent
        }
    }

    private var userId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_main)
        this.initializeActivity(savedInstanceState)
    }

    /**
     * Initializes this activity.
     */
    private fun initializeActivity(savedInstanceState: Bundle?) {
        setSupportActionBar(toolbar_main)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        //Listener can be replace with a lambda
        toolbar_main.setNavigationOnClickListener { onBackPressed() }

        if (savedInstanceState == null) {
            userId = intent.getIntExtra(INTENT_EXTRA_PARAM_USER_ID, -1)
            val fragment = UserDetailsFragment.newInstance(userId)
            replaceFragment(R.id.content_frame, fragment)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(INSTANCE_STATE_PARAM_USER_ID, userId)
        super.onSaveInstanceState(outState)
    }
}