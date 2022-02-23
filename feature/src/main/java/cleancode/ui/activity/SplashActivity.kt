package cleancode.ui.activity

import android.os.Bundle
import cleancode.ui.base.BaseActivity
import android.os.Handler
import com.nygar.feature.R
import com.nygar.feature.databinding.ActivityLayoutMainBinding
import com.nygar.feature.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main application screen. This is the app entry point.
 */
@AndroidEntryPoint
class SplashActivity: BaseActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler().postDelayed({ navigator.navigateToLogin(this) }, 2000)
    }
}