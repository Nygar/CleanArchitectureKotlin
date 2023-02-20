package cleancode.ui.activity

import android.content.SharedPreferences
import android.os.Bundle
import cleancode.ui.base.BaseActivity
import android.os.Handler
import android.os.Looper
import com.nygar.feature.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * Main application screen. This is the app entry point.
 */
@AndroidEntryPoint
class SplashActivity: BaseActivity() {

    @Inject lateinit var sharedPreferences: SharedPreferences

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Handler(Looper.getMainLooper()).postDelayed({ navigator.navigateToLogin(this) }, 2000)
        sharedPreferences.getBoolean("Debug",false)
    }
}