package com.pixelart.githubapiusers.ui.usersscreen


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.pixelart.githubapiusers.AppController
import com.pixelart.githubapiusers.R
import com.pixelart.githubapiusers.adapter.UsersAdapter
import com.pixelart.githubapiusers.common.RxBus
import com.pixelart.githubapiusers.data.dto.AllUsers
import com.pixelart.githubapiusers.di.fragment.FragmentModule
import kotlinx.android.synthetic.main.fragment_home.view.*
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class UsersFragment : Fragment(), UsersAdapter.OnItemClickedListener {
    @Inject lateinit var viewModel: AllUsersViewModel

    private lateinit var rootView: View
    private lateinit var adapter: UsersAdapter
    private lateinit var userList: ArrayList<AllUsers>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragmentComponent = (activity?.application as AppController)
            .applicationComponent
            .newFragmentComponent(FragmentModule(this))
        fragmentComponent.injectUsersScreen(this)

        viewModel.getAllUsers()
        adapter = UsersAdapter(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rootView.rvUsers.apply {
            layoutManager = LinearLayoutManager(this@UsersFragment.context)
            addItemDecoration(DividerItemDecoration(this@UsersFragment.context, LinearLayoutManager.VERTICAL))
            adapter = this@UsersFragment.adapter
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getUsersLiveData().observe(this, Observer {users->
            adapter.submitList(users)
            userList = users as ArrayList<AllUsers>
        })
    }

    override fun onItemClicked(position: Int) {
        RxBus.INSTANCE.post(userList[position].login)
        val itemView = rootView.rvUsers.findViewHolderForAdapterPosition(position)?.itemView

        val action = UsersFragmentDirections.actionUserToProfile()
        itemView?.let { Navigation.findNavController(it).navigate(action) }
    }
}
