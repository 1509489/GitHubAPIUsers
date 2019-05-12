package com.pixelart.githubapiusers

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.*
import com.pixelart.githubapiusers.data.dto.SingleUser
import com.pixelart.githubapiusers.data.network.NetworkService
import com.pixelart.githubapiusers.data.repository.SingleUserRepository
import com.pixelart.githubapiusers.ui.profilescreen.ProfileViewModel
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import retrofit2.Response

class ProfileViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ProfileViewModel
    private lateinit var repository: SingleUserRepository
    private lateinit var user: SingleUser

    private val networkService: NetworkService = mock()
    private val singleUserObser: Observer<SingleUser> = mock()

    @Before
    fun setUp() {
        repository = SingleUserRepository(networkService)
        viewModel = ProfileViewModel(repository)

        user = SingleUser("some login", 1, "", "avatar url", "", "","",
            "","","","","","","",
            "","","User", false, "some user", "", "",
            "","","","",55, 25, 80, 33,
            "", "")
    }

    @Test
    fun `On Fetch Single User`() {
        val output: Response<SingleUser> = Response.success(user)
        val response: Deferred<Response<SingleUser>> = CompletableDeferred(output)

        runBlocking {
            whenever(networkService.getSingleUser(anyString())).thenReturn(response)
            viewModel.apply {
                getUser("some login")
                getUsersLiveData().observeForever(singleUserObser)
            }
        }

        val captor = argumentCaptor<SingleUser>()
        verify(singleUserObser).onChanged(captor.capture())
        Assert.assertTrue(captor.firstValue.login == "some login")
        Assert.assertEquals(captor.firstValue.avatarUrl,"avatar url")
        verifyNoMoreInteractions(singleUserObser)
    }
}