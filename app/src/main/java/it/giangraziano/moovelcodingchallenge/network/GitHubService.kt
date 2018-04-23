package it.giangraziano.moovelcodingchallenge.network

import io.reactivex.Single
import it.giangraziano.moovelcodingchallenge.model.GitHubUser
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubService {
    @GET("/users/")
    fun getDevelopers(
            @Query("since") since: Int
    ): Single<MutableList<GitHubUser>>

}