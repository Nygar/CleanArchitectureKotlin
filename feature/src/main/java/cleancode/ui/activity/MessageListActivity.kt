package cleancode.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import cleancode.model.MessageModel
import cleancode.ui.base.BaseActivity
import cleancode.ui.fragment.MessageListFragment
import com.nygar.feature.R
import com.nygar.feature.databinding.ActivityLayoutMainBinding

/**
 * Activity with navigation drawer
 */
class MessageListActivity : BaseActivity(), MessageListFragment.MessageListListener {

    companion object {

        private const val INTENT_EXTRA_PARAM_CATEGORY_ID = "com.nygar.INTENT_PARAM_CATEGORY_ID"
        private const val INSTANCE_STATE_PARAM_CATEGORY_ID = "com.nygar.STATE_PARAM_CATEGORY_ID"


        fun getCallingIntent(context: Context, categoryId: Int): Intent {
            val callingIntent = Intent(context, MessageListActivity::class.java)
            callingIntent.putExtra(INTENT_EXTRA_PARAM_CATEGORY_ID, categoryId)
            return callingIntent
        }
    }

    private lateinit var binding: ActivityLayoutMainBinding

    private var categoryId: Int = 0

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
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        //Listener can be replace with a lambda
        binding.mainLayout.toolbarMainLayout.toolbarMain.setNavigationOnClickListener { onBackPressed() }

        if (savedInstanceState == null) {
            categoryId = intent.getIntExtra(INTENT_EXTRA_PARAM_CATEGORY_ID, -1)
            val fragment = MessageListFragment.newInstance(categoryId)
            replaceFragment(R.id.content_frame, fragment)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(INSTANCE_STATE_PARAM_CATEGORY_ID, categoryId)
        super.onSaveInstanceState(outState)
    }

    override fun onMessageClicked(messageModel: MessageModel) {
        this.navigator.navigateToMessageDetails(this, messageModel.messageId)
    }
}