package cleancode.ui.activity

import android.os.Bundle
import cleancode.ui.base.BaseActivity
import android.os.Handler
import com.nygar.feature.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main application screen. This is the app entry point.
 */
@AndroidEntryPoint
class SplashActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({ navigator.navigateToLogin(this) }, 2000)
    }
}