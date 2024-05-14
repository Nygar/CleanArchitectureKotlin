package cleancode.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cleancode.ui.navigation.ARGUMENT_MESSAGE_LIST_ID
import cleancode.ui.navigation.MessageNavigation
import com.nygar.designsystem.theme.CleanArchitectureKotlinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessageActivity : ComponentActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val categoryId = savedInstanceState?.getInt(ARGUMENT_MESSAGE_LIST_ID) ?: 0

        setContent {
            CleanArchitectureKotlinTheme {
                MessageNavigation(categoryId){
                    onBackPressedDispatcher.onBackPressed()
                }
            }
        }
    }
}