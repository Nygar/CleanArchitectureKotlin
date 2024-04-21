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
import com.nygar.feature.databinding.FragmentCategoryListBinding

/**
 * Fragment that shows a list of Message.
 */

class MessageCategoryFragment: BaseFragment(){

    interface MessageCategoryView{
        fun onCategoryClicked(category: CategoryModel)
    }

    private val viewModel: MessageCategoryViewModel by viewModels()

    private lateinit var binding: FragmentCategoryListBinding

    private lateinit var viewInterface: MessageCategoryView

    private val adapter: CategoriesAdapter = CategoriesAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewInterface = context as MessageCategoryView
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentCategoryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        /*
        viewModel.getMessageCategory().observe(viewLifecycleOwner) { data ->
            adapter.setCategoriesCollection(data)
        }

         */
    }

    private fun setupRecyclerView() {
        adapter.setOnItemClickListener(onItemClickListener)
        binding.rvCategories.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL ,false)
        binding.rvCategories.adapter = adapter
    }

    private val onItemClickListener : CategoriesAdapter.ItemClickListener = object : CategoriesAdapter.ItemClickListener {
        override fun clickItem(item: CategoryModel) {
            viewInterface.onCategoryClicked(item)
        }
    }

}