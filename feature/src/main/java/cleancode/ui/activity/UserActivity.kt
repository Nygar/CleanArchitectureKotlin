package cleancode.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import cleancode.ui.base.BaseActivity
import cleancode.ui.fragment.UserDetailsFragment
import com.nygar.feature.R
import com.nygar.feature.databinding.ActivityLayoutMainBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Activity with navigation drawer
 */
@AndroidEntryPoint
class UserActivity : BaseActivity() {

    companion object {

        private const val INTENT_EXTRA_PARAM_CATEGORY_ID = "com.nygar.INTENT_PARAM_USER_ID"
        private const val INSTANCE_STATE_PARAM_CATEGORY_ID = "com.nygar.STATE_PARAM_USER_ID"


        fun getCallingIntent(context: Context, categoryId: Int): Intent {
            val callingIntent = Intent(context, UserActivity::class.java)
            callingIntent.putExtra(INTENT_EXTRA_PARAM_CATEGORY_ID, categoryId)
            return callingIntent
        }
    }

    private lateinit var binding: ActivityLayoutMainBinding

    private var userId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLayoutMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.initializeActivity(savedInstanceState)
    }

    /**
     * Initializes this activity.
     */
    private fun initializeActivity(savedInstanceState: Bundle?) {
        setSupportActionBar(binding.mainLayout.toolbarMainLayout.toolbarMain )
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //Listener can be replace with a lambda
        binding.mainLayout.toolbarMainLayout.toolbarMain.setNavigationOnClickListener { onBackPressedDispatcher.onBackPressed() }

        if (savedInstanceState == null) {
            userId = intent.getIntExtra(INTENT_EXTRA_PARAM_CATEGORY_ID, -1)
            val fragment = UserDetailsFragment.newInstance(userId)
            addFragment(R.id.content_frame, fragment)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(INSTANCE_STATE_PARAM_CATEGORY_ID, userId)
        super.onSaveInstanceState(outState)
    }
}