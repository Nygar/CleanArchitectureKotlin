package cleancode.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cleancode.model.MessageModel
import com.nygar.feature.databinding.RowMessageBinding

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
        val binding = RowMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MessageViewHolder(binding)
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

    @SuppressLint("NotifyDataSetChanged")
    fun setMessagesCollection(messagesCollection: Collection<MessageModel>) {
        MessagesAdapter.messagesCollection = messagesCollection as List<MessageModel>
        this.notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: ItemClickListener) {
        MessagesAdapter.onItemClickListener = onItemClickListener
    }

    internal class MessageViewHolder(itemView: RowMessageBinding) : RecyclerView.ViewHolder(itemView.root) {

        var textViewName: TextView = itemView.tvName

    }
}