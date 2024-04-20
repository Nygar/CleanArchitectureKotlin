package cleancode.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import cleancode.ui.navigation.Navigation
import com.nygar.designsystem.theme.CleanArchitectureKotlinTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Activity with navigation drawer
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        // Handle the splash screen transition.
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContent {
            CleanArchitectureKotlinTheme {
                Navigation()
            }
        }
    }
}