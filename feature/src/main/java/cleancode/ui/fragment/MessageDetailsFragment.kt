package cleancode.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import cleancode.ui.base.BaseFragment
import cleancode.ui.util.GlideApp
import cleancode.viewmodel.MessageDetailsViewModel
import kotlinx.android.synthetic.main.view_message_details.*
import androidx.fragment.app.viewModels
import cleancode.ui.base.withArgs
import com.nygar.feature.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment that shows details of a certain user.
 */

@AndroidEntryPoint
class MessageDetailsFragment : BaseFragment() {

    companion object {

        private const val MESSAGE_KEY:String = "categoryId"

        fun newInstance(messageId: Int) = MessageDetailsFragment().withArgs {
            putInt(MESSAGE_KEY, messageId)
        }
    }

    private val viewModel: MessageDetailsViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_message_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(MESSAGE_KEY)?.let {
            viewModel.getMessageById(it).observe(viewLifecycleOwner, Observer { data ->
                GlideApp.with(this).load(data.imageUrl).into(iv_image)
                tv_name.text = data.name
                tv_description.text = data.description
            })
        }
    }
}