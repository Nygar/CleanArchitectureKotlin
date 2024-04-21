package cleancode.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cleancode.model.UserModel
import cleancode.ui.adapter.UsersAdapter
import cleancode.ui.base.BaseFragment
import cleancode.viewmodel.UserListViewModel
import com.nygar.feature.databinding.FragmentUserListBinding

/**
 * Fragment that shows a list of Users.
 */

class UserListFragment : BaseFragment() {

    /**
     * Interface for listening user list events.
     */
    interface UserListView {
        fun onUserClicked(userModel: UserModel)
    }

    private lateinit var binding: FragmentUserListBinding

    private val viewModel: UserListViewModel by viewModels()

    private lateinit var viewInterface: UserListView

    private val adapter: UsersAdapter = UsersAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewInterface = context as UserListView
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        /*
        viewModel.getUserList().observe(viewLifecycleOwner) { data ->
            adapter.setUsersCollection(data)
        }

         */
    }
    private fun setupRecyclerView() {
        adapter.setOnItemClickListener(onItemClickListener)
        binding.rvUsers.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL ,false)
        binding.rvUsers.adapter = adapter
    }

    private val onItemClickListener : UsersAdapter.ItemClickListener = object : UsersAdapter.ItemClickListener {
        override fun clickItem(item: UserModel) {
            viewInterface.onUserClicked(item)
        }
    }

}