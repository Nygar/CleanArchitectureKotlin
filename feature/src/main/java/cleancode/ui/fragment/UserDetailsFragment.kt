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
import com.nygar.feature.databinding.FragmentUserDetailsBinding

/**
 * Fragment that shows details of a certain user.
 */

class UserDetailsFragment : BaseFragment() {

    companion object {

        private const val USER_DETAILS_KEY:String = "categoryId"

        fun newInstance(userId: Int) = UserDetailsFragment().withArgs {
            putInt(USER_DETAILS_KEY, userId)
        }
    }

    private lateinit var binding: FragmentUserDetailsBinding

    private val viewModel: UserDetailsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentUserDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(USER_DETAILS_KEY)?.let {
            viewModel.getUserById(it).observe(viewLifecycleOwner) { data ->
                GlideApp.with(this).load(data.coverUrl).into(binding.viewUserDetail.ivCover)
                binding.viewUserDetail.tvFullname.text = data.fullName
                binding.viewUserDetail.tvEmail.text = data.email
                binding.viewUserDetail.tvFollowers.text = data.followers.toString()
                binding.viewUserDetail.tvDescription.text = data.description
            }
        }
    }
}