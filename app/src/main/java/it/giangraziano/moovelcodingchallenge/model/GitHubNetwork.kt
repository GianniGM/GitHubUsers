package it.giangraziano.moovelcodingchallenge.model

import io.reactivex.Single

interface GitHubNetwork {
    fun getGitHubUsersFromApi(): Single<Response>?
    fun getGitHubUserInfo(username: String): Single<GitHubUser>?
}