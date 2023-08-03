package cleancode.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import cleancode.ui.util.Analytics
import cleancode.ui.util.Navigator

abstract class BaseActivityCompose: ComponentActivity(), BaseActivityComposeDelegate {

    val navigator: Navigator = Navigator
    lateinit var analytics: Analytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            UI()
        }
    }
}

interface BaseActivityComposeDelegate{
    @Composable
    fun UI()
}