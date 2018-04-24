package it.giangraziano.moovelcodingchallenge.network

import io.reactivex.Single
import it.giangraziano.moovelcodingchallenge.model.GitHubUser
import it.giangraziano.moovelcodingchallenge.model.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubService {
    @GET("/users")
    fun getDeveloper(@Query("username") username: String): Single<GitHubUser>

    @GET("/search/users")
    fun searchDevelopers(
            @Query("q") q: String,
            @Query("page") page: Int,
            @Query("type") type: String
    ): Single<Response>
}