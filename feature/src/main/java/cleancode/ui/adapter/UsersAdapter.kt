package cleancode.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cleancode.model.UserModel
import com.nygar.feature.databinding.RowUserBinding

/**
 * Adapter that manages a collection of [UserModel].
 */
internal object UsersAdapter: RecyclerView.Adapter<UsersAdapter.CategoryViewHolder>() {

    interface ItemClickListener{
        fun clickItem(item: UserModel)
    }

    private lateinit var onItemClickListener: ItemClickListener
    private var userCollection: List<UserModel> = ArrayList()

    override fun getItemCount(): Int {
        return userCollection.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = RowUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = userCollection[position]
        holder.textViewName.text = item.fullName
        /*
        GlideApp.with(holder.itemView.context)
            //.load(item.coverUrl)
            .load(R.drawable.ic_person_black_24dp)
            .placeholder(R.drawable.ic_person_black_24dp)
            .into(holder.avatarImageView)

         */
        holder.itemView.setOnClickListener {
            onItemClickListener.clickItem(item)
        }
    }

    override fun getItemId(position: Int): Long {
        return userCollection[position].userId.toLong()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setUsersCollection(userCollection: Collection<UserModel>) {
        UsersAdapter.userCollection = userCollection as List<UserModel>
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: ItemClickListener) {
        UsersAdapter.onItemClickListener = onItemClickListener
    }

    internal class CategoryViewHolder(itemView: RowUserBinding) : RecyclerView.ViewHolder(itemView.root) {
        val textViewName: TextView = itemView.tvTitle
        val avatarImageView: ImageView = itemView.ivAvatar
    }
}