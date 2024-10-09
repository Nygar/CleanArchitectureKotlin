package cleancode.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import cleancode.ui.navigation.ARGUMENT_MESSAGE_LIST_ID
import cleancode.ui.navigation.MainNavigation
import cleancode.ui.util.Analytics
import com.google.firebase.analytics.FirebaseAnalytics
import com.nygar.designsystem.theme.CleanArchitectureKotlinTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Activity with navigation drawer
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var analytics: Analytics

    override fun onCreate(savedInstanceState: Bundle?) {
        // Handle the splash screen transition.
        installSplashScreen()

        val firebaseAnalytics = FirebaseAnalytics.getInstance(this)
        analytics = Analytics(firebaseAnalytics)
        analytics.sendAnalyticViewScreen(this::class.java.simpleName)

        super.onCreate(savedInstanceState)
        setContent {
            CleanArchitectureKotlinTheme {
                MainNavigation(
                    onNavigateToMessage = {
                        val intent = Intent(this, MessageActivity::class.java)
                        intent.putExtra(ARGUMENT_MESSAGE_LIST_ID, it)
                        startActivity(intent)
                    },
                )
            }
        }
    }
}
