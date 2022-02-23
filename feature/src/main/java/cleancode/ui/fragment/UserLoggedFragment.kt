package cleancode.ui.fragment

import cleancode.ui.base.BaseFragment
import android.os.Bundle
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import cleancode.ui.util.GlideApp
import cleancode.viewmodel.UserLoggedViewModel
import com.nygar.feature.databinding.HeaderProfileBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment that shows details of a certain user.
 */

@AndroidEntryPoint
class UserLoggedFragment: BaseFragment(){

    private lateinit var binding: HeaderProfileBinding

    private val viewModel: UserLoggedViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = HeaderProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUserLogged().observe(viewLifecycleOwner) { user ->
            binding.textViewName.text = user.fullName
            GlideApp.with(this)
                .load(user.avatarUrl)
                .into(binding.imageViewAvatar)
        }
    }

}