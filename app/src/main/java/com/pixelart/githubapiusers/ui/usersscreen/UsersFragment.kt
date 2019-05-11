package com.pixelart.githubapiusers.ui.usersscreen


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.pixelart.githubapiusers.AppController
import com.pixelart.githubapiusers.R
import com.pixelart.githubapiusers.di.fragment.FragmentModule
import javax.inject.Inject

class UsersFragment : Fragment() {
    @Inject lateinit var viewModel: AllUsersViewModel

    private lateinit var rootView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragmentComponent = (activity?.application as AppController)
            .applicationComponent
            .newFragmentComponent(FragmentModule(this))
        fragmentComponent.injectUsersScreen(this)

        viewModel.getAllUsers()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home, container, false)
        return rootView
    }

    override fun onResume() {
        super.onResume()
        viewModel.getUsersLiveData().observe(this, Observer {

        })
    }
}
