package cleancode.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import cleancode.ui.base.BaseActivity
import cleancode.ui.fragment.MessageDetailsFragment
import com.nygar.feature.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.toolbar_main.*

/**
 * Activity that shows details of a certain message.
 */
@AndroidEntryPoint
class MessageDetailsActivity : BaseActivity() {

    companion object {

        private const val INTENT_EXTRA_PARAM_MESSAGE_ID = "com.nygar.INTENT_PARAM_MESSAGE_ID"

        fun getCallingIntent(context: Context, messageId: Int): Intent {
            val callingIntent = Intent(context, MessageDetailsActivity::class.java)
            callingIntent.putExtra(INTENT_EXTRA_PARAM_MESSAGE_ID, messageId)
            return callingIntent
        }
    }

    private var messageId: Int = 0

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
            messageId = intent.getIntExtra(INTENT_EXTRA_PARAM_MESSAGE_ID, -1)
            val fragment = MessageDetailsFragment.newInstance(messageId)
            replaceFragment(R.id.content_frame, fragment)
        }
    }
}
