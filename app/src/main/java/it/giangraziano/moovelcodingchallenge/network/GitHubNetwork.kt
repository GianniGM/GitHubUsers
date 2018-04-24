package it.giangraziano.moovelcodingchallenge.network

import io.reactivex.Single
import it.giangraziano.moovelcodingchallenge.model.GitHubUser
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GitHubNetwork(private val retrofit: GitHubService) {

    companion object {
        fun create() = GitHubNetwork(Retrofit.Builder()
                .baseUrl(NetworkData.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(GitHubService::class.java) )
    }

    fun getGitHubUsersFromApi(): Single<MutableList<GitHubUser>>? {
        return retrofit.getDevelopers(135)
    }
}
