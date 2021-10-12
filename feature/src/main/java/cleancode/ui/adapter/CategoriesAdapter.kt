package cleancode.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import cleancode.model.CategoryModel
import com.nygar.feature.R
import kotlinx.android.synthetic.main.row_category.view.*

/**
 * Adapter that manages a collection of [CategoryModel].
 */
internal object CategoriesAdapter: RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder>() {

    interface ItemClickListener{
        fun clickItem(item: CategoryModel)
    }

    private lateinit var onItemClickListener: ItemClickListener
    private var categoriesCollection: List<CategoryModel> = ArrayList()

    override fun getItemCount(): Int {
        return categoriesCollection.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val categoryModel = categoriesCollection[position]
        holder.textViewName.text = categoryModel.name
        holder.itemView.setOnClickListener {
            onItemClickListener.clickItem(categoryModel)
        }
    }

    override fun getItemId(position: Int): Long {
        return categoriesCollection[position].categoryId.toLong()
    }

    fun setCategoriesCollection(categoriesCollection: Collection<CategoryModel>) {
        CategoriesAdapter.categoriesCollection = categoriesCollection as List<CategoryModel>
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(onItemClickListener: ItemClickListener) {
        CategoriesAdapter.onItemClickListener = onItemClickListener
    }

    internal class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewName: TextView = itemView.tv_name
    }
}