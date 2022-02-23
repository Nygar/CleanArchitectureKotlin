package cleancode.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cleancode.ui.base.BaseFragment
import cleancode.ui.util.GlideApp
import cleancode.viewmodel.MessageDetailsViewModel
import androidx.fragment.app.viewModels
import cleancode.ui.base.withArgs
import com.nygar.feature.databinding.FragmentMessageDetailsBinding

/**
 * Fragment that shows details of a certain user.
 */

class MessageDetailsFragment : BaseFragment() {

    companion object {

        private const val MESSAGE_KEY:String = "categoryId"

        fun newInstance(messageId: Int) = MessageDetailsFragment().withArgs {
            putInt(MESSAGE_KEY, messageId)
        }
    }

    private lateinit var binding: FragmentMessageDetailsBinding

    private val viewModel: MessageDetailsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentMessageDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(MESSAGE_KEY)?.let {
            viewModel.getMessageById(it).observe(viewLifecycleOwner) { data ->
                GlideApp.with(this).load(data.imageUrl).into(binding.viewMessageDetail.ivImage)
                binding.viewMessageDetail.tvName.text = data.name
                binding.viewMessageDetail.tvDescription.text = data.description
            }
        }
    }
}