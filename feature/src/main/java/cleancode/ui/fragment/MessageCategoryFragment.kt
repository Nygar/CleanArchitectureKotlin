package cleancode.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cleancode.model.CategoryModel
import cleancode.ui.adapter.CategoriesAdapter
import cleancode.ui.base.BaseFragment
import cleancode.viewmodel.MessageCategoryViewModel
import com.nygar.feature.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_category_list.*

/**
 * Fragment that shows a list of Message.
 */

@AndroidEntryPoint
class MessageCategoryFragment: BaseFragment(){

    interface MessageCategoryView{
        fun onCategoryClicked(category: CategoryModel)
    }

    private val viewModel: MessageCategoryViewModel by viewModels()

    private lateinit var viewInterface: MessageCategoryView

    private val adapter: CategoriesAdapter = CategoriesAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewInterface = context as MessageCategoryView
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_category_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.getMessageCategory().observe(viewLifecycleOwner, { data ->
            adapter.setCategoriesCollection(data)
        })
    }

    private fun setupRecyclerView() {
        adapter.setOnItemClickListener(onItemClickListener)
        rv_categories.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL ,false)
        rv_categories.adapter = adapter
    }

    private val onItemClickListener : CategoriesAdapter.ItemClickListener = object : CategoriesAdapter.ItemClickListener {
        override fun clickItem(item: CategoryModel) {
            viewInterface.onCategoryClicked(item)
        }
    }

}