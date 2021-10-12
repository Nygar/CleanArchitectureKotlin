package cleancode.ui.fragment

import cleancode.ui.base.BaseFragment
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import cleancode.ui.util.GlideApp
import cleancode.viewmodel.UserLoggedViewModel
import com.nygar.feature.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.header_profile.*

/**
 * Fragment that shows details of a certain user.
 */

@AndroidEntryPoint
class UserLoggedFragment: BaseFragment(){

    private val viewModel: UserLoggedViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.header_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserLogged().observe(viewLifecycleOwner, Observer { user ->
            textView_Name.text = user.fullName
            GlideApp.with(this)
                .load(user.avatarUrl)
                .into(imageView_Avatar)
        })
    }

}