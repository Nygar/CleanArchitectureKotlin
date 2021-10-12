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
import com.nygar.feature.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_user_list.*

/**
 * Fragment that shows a list of Users.
 */

@AndroidEntryPoint
class UserListFragment : BaseFragment() {

    /**
     * Interface for listening user list events.
     */
    interface UserListView {
        fun onUserClicked(userModel: UserModel)
    }

    private val viewModel: UserListViewModel by viewModels()

    private lateinit var viewInterface: UserListView

    private val adapter: UsersAdapter = UsersAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewInterface = context as UserListView
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        viewModel.getUserList().observe(viewLifecycleOwner, { data ->
            adapter.setUsersCollection(data)
        })
    }
    private fun setupRecyclerView() {
        adapter.setOnItemClickListener(onItemClickListener)
        rv_users.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL ,false)
        rv_users.adapter = adapter
    }

    private val onItemClickListener : UsersAdapter.ItemClickListener = object : UsersAdapter.ItemClickListener {
        override fun clickItem(item: UserModel) {
            viewInterface.onUserClicked(item)
        }
    }

}