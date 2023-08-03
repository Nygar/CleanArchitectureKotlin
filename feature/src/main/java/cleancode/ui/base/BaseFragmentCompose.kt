package cleancode.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment

abstract class BaseFragmentCompose : Fragment(), ComposeFragmentDelegate {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = createComposeView(inflater.context)
}

interface ComposeFragmentDelegate{

    @Composable
    fun UI()

    fun createComposeView(context: Context) =
        ComposeView(context).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                UI()
            }
        }
}