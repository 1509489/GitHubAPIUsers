package com.pixelart.githubapiusers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import com.pixelart.githubapiusers.data.dto.AllUsers
import com.pixelart.githubapiusers.data.network.NetworkService
import com.pixelart.githubapiusers.data.repository.AllUsersRepository
import com.pixelart.githubapiusers.ui.usersscreen.AllUsersViewModel
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class AllUsersViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: AllUsersViewModel
    private lateinit var repository: AllUsersRepository
    private lateinit var allUsers: ArrayList<AllUsers>

    private val networkService: NetworkService = mock()
    private val allUsersObserver: Observer<List<AllUsers>> = mock()

    @Before
    fun setUp(){
        repository = AllUsersRepository(networkService)
        viewModel = AllUsersViewModel(repository)
        allUsers = ArrayList()

        for (i in 0 until 10){
            allUsers.add(AllUsers("some username $i", i, "", "some url $i", "", "",
                "", "", "", "", "", "", "",
                "", "", "", "User", false))
        }
    }

    @Test
    fun `On Fetch Users`(){
        val output:Response<List<AllUsers>> = Response.success(allUsers)
        val response: Deferred<Response<List<AllUsers>>> = CompletableDeferred(output)

        runBlocking {
            whenever(networkService.getAllUsers()).thenReturn(response)
            viewModel.apply {
                getAllUsers()
                getUsersLiveData().observeForever(allUsersObserver)
            }
        }

        val captor = argumentCaptor<List<AllUsers>>()
        verify(allUsersObserver).onChanged(captor.capture())
        Assert.assertTrue(captor.firstValue.size == 10)
        Assert.assertEquals(captor.firstValue[4].login,"some username 4")
        Assert.assertEquals(captor.firstValue[6].avatarUrl,"some url 6")
        verifyNoMoreInteractions(allUsersObserver)
    }
}