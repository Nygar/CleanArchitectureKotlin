package cleancode.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cleancode.model.MessageModel
import com.nygar.feature.R
import kotlinx.android.synthetic.main.row_message.view.*

/**
* Adapter that manages a collection of  [MessageModel].
*/
internal object MessagesAdapter: RecyclerView.Adapter<MessagesAdapter.MessageViewHolder>() {

    interface ItemClickListener {
        fun clickItem(item: MessageModel)
    }

    private var messagesCollection: List<MessageModel> = ArrayList()

    private lateinit var onItemClickListener: ItemClickListener

    override fun getItemCount(): Int {
        return messagesCollection.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_message, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val messageModel = messagesCollection[position]
        holder.textViewName.text=messageModel.name
        holder.itemView.setOnClickListener {
            onItemClickListener.clickItem(messageModel)
        }
    }

    override fun getItemId(position: Int): Long {
        return messagesCollection[position].messageId.toLong()
    }

    fun setMessagesCollection(messagesCollection: Collection<MessageModel>) {
        MessagesAdapter.messagesCollection = messagesCollection as List<MessageModel>
        this.notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: ItemClickListener) {
        MessagesAdapter.onItemClickListener = onItemClickListener
    }

    internal class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textViewName: TextView = itemView.tv_name

    }
}