package cleancode.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cleancode.model.MessageModel
import cleancode.ui.adapter.MessagesAdapter
import cleancode.ui.base.BaseFragment
import cleancode.ui.base.withArgs
import cleancode.viewmodel.MessageListViewModel
import com.nygar.feature.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_message_list.*

/**
 * Fragment that shows a list of Message.
 */

@AndroidEntryPoint
class MessageListFragment: BaseFragment() {

    companion object {

        private const val CATEGORY_KEY:String = "categoryId"

        fun newInstance(categoryId: Int) = MessageListFragment().withArgs {
            putInt(CATEGORY_KEY, categoryId)
        }
    }

    /**
     * Interface for listening message list events.
     */
    interface MessageListListener {
        fun onMessageClicked(messageModel: MessageModel)
    }

    private val viewModel: MessageListViewModel by viewModels()

    private val adapter: MessagesAdapter = MessagesAdapter

    private lateinit var messageListListener: MessageListListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        messageListListener = context as MessageListListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_message_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.getMessageList(arguments?.get(CATEGORY_KEY) as Int).observe(viewLifecycleOwner, { data ->
            adapter.setMessagesCollection(data)
        })
    }

    private fun setupRecyclerView() {
        adapter.setOnItemClickListener(onItemClickListener)
        rv_messages.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL ,false)
        rv_messages.adapter = adapter
    }

    private val onItemClickListener : MessagesAdapter.ItemClickListener = object : MessagesAdapter.ItemClickListener {
        override fun clickItem(item: MessageModel) {
            messageListListener.onMessageClicked(item)
        }
    }

}