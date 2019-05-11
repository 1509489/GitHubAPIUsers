package com.pixelart.githubapiusers.ui.profilescreen


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.pixelart.githubapiusers.AppController
import com.pixelart.githubapiusers.R
import com.pixelart.githubapiusers.common.GlideApp
import com.pixelart.githubapiusers.common.RxBus
import com.pixelart.githubapiusers.di.fragment.FragmentModule
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.fragment_profile.view.*
import javax.inject.Inject

class ProfileFragment : Fragment() {
    private lateinit var rootView: View

    private var user = ""

    @Inject lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fragmentComponent = (activity?.application as AppController)
            .applicationComponent
            .newFragmentComponent(FragmentModule(this))
        fragmentComponent.injectProfileScreen(this)

        RxBus.INSTANCE.subscribe(Consumer { data ->
            if(data is String){
                user = data
            }
        })

        viewModel.getUser(user)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_profile, container, false)
        return rootView
    }

    override fun onResume() {
        super.onResume()
        viewModel.getUsersLiveData().observe(this, Observer {user ->
            rootView.tvUsername.text = user.login
            rootView.tvName.text = user.name
            rootView.tvRepos.text = user.publicRepos.toString()
            rootView.tvGists.text = user.publicGists.toString()
            rootView.tvFollowers.text = user.followers.toString()
            rootView.tvFollowing.text = user.following.toString()

            activity?.applicationContext?.let {
                GlideApp.with(it)
                    .load(user.avatarUrl)
                    .into(rootView.ivAvatar)
            }
        })
    }
}
