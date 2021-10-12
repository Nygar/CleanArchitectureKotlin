package cleancode.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import cleancode.ui.base.BaseFragment
import cleancode.ui.base.withArgs
import cleancode.ui.util.GlideApp
import cleancode.viewmodel.UserDetailsViewModel
import com.nygar.feature.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.view_user_details.*

/**
 * Fragment that shows details of a certain user.
 */

@AndroidEntryPoint
class UserDetailsFragment : BaseFragment() {

    companion object {

        private const val USER_DETAILS_KEY:String = "categoryId"

        fun newInstance(userId: Int) = UserDetailsFragment().withArgs {
            putInt(USER_DETAILS_KEY, userId)
        }
    }

    private val viewModel: UserDetailsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(USER_DETAILS_KEY)?.let {
            viewModel.getUserById(it).observe(viewLifecycleOwner, { data ->
                GlideApp.with(this).load(data.coverUrl).into(iv_cover)
                tv_fullname.text = data.fullName
                tv_email.text = data.email
                tv_followers.text = data.followers.toString()
                tv_description.text = data.description
            })
        }
    }
}