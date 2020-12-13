package com.project.githubuser.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.project.githubuser.data.api.GithubAPI
import com.project.githubuser.data.api.SearchResponse
import com.project.githubuser.data.model.GithubUser
import retrofit2.*

class GithubViewModel:  ViewModel(){
    private var githubAPI = GithubAPI()
    private var userList = MutableLiveData<ArrayList<GithubUser>>()
    private var detailData = MutableLiveData<GithubUser>()
    var isLoading = true
    var dataExist = false

    fun getUsers(): LiveData<ArrayList<GithubUser>> = userList

    fun setGithubUsers(){
        isLoading = true
        githubAPI.githubInterface?.getUsersAll()?.enqueue(object : Callback<ArrayList<GithubUser>>{
            override fun onFailure(call: Call<ArrayList<GithubUser>>, t: Throwable) {
                Log.d("onFailure", "Gagal ambil data")
            }

            override fun onResponse(
                call: Call<ArrayList<GithubUser>>,
                response: Response<ArrayList<GithubUser>>
            ) {
                try {
                    val githubUserResponse = response.body()
                    if (githubUserResponse != null){
                        userList.postValue(githubUserResponse)
                        dataExist = true
                    }
                } catch (e: Exception) {
                    // belum diisi
                } finally {
                    isLoading = false
                }
            }

        })
    }

    fun searchGithubUser(login: String){
        isLoading = true
        githubAPI.githubInterface?.getUsersSearch(login)?.enqueue(object : Callback<SearchResponse> {
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                try {
                    val githubUserResponse = response.body()
                    if (githubUserResponse != null){
                        userList.postValue(githubUserResponse.items)
                        dataExist = true
                    }
                } catch (e: Exception) {
                    // belum diisi
                } finally {
                    isLoading = false
                }
            }

        })
    }

    fun getDetailUser(): LiveData<GithubUser> = detailData

    fun setDetailUser(login: String){
        isLoading = true
        githubAPI.githubInterface?.getUserDetail(login)?.enqueue(object : Callback<GithubUser>{
            override fun onFailure(call: Call<GithubUser>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<GithubUser>, response: Response<GithubUser>) {
                try {
                    val githubUserResponse = response.body()
                    if (githubUserResponse != null){
                        detailData.postValue(githubUserResponse)
                        dataExist = true
                    }
                } catch (e: Exception) {
                    // belum diisi
                } finally {
                    isLoading = false
                }
            }

        })

    }

    fun getFollowersUser(): LiveData<ArrayList<GithubUser>> = userList

    fun setFollowersUser(login: String){
        isLoading = true
        githubAPI.githubInterface?.getUserFollowers(login)?.enqueue(object : Callback<ArrayList<GithubUser>>{
            override fun onFailure(call: Call<ArrayList<GithubUser>>, t: Throwable) {
                Log.d("onFailure", "Gagal ambil data")
            }

            override fun onResponse(
                    call: Call<ArrayList<GithubUser>>,
                    response: Response<ArrayList<GithubUser>>
            ) {
                try {
                    val githubUserResponse = response.body()
                    if (githubUserResponse != null){
                        userList.postValue(githubUserResponse)
                        dataExist = true
                    }
                } catch (e: Exception) {
                    // belum diisi
                } finally {
                    isLoading = false
                }
            }

        })
    }

    fun getFollowingUser(): LiveData<ArrayList<GithubUser>> = userList

    fun setFollowingUser(login: String){
        isLoading = true
        githubAPI.githubInterface?.getUserFollowing(login)?.enqueue(object : Callback<ArrayList<GithubUser>>{
            override fun onFailure(call: Call<ArrayList<GithubUser>>, t: Throwable) {
                Log.d("onFailure", "Gagal ambil data")
            }

            override fun onResponse(
                    call: Call<ArrayList<GithubUser>>,
                    response: Response<ArrayList<GithubUser>>
            ) {
                try {
                    val githubUserResponse = response.body()
                    if (githubUserResponse != null){
                        userList.postValue(githubUserResponse)
                        dataExist = true
                    }
                } catch (e: Exception) {
                    // belum diisi
                } finally {
                    isLoading = false
                }
            }

        })
    }
}