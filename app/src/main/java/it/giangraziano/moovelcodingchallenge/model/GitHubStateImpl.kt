package it.giangraziano.moovelcodingchallenge.model

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import it.giangraziano.moovelcodingchallenge.network.GitHubService
import it.giangraziano.moovelcodingchallenge.network.NetworkData
import it.giangraziano.moovelcodingchallenge.mainView.USERS_TO_LOAD_AT_ONCE
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.properties.Delegates

class GitHubStateImpl(private val retrofit: GitHubService) : GitHubNetwork, GitHubState {

    private var currentDevelopers by Delegates.observable(mutableListOf<GitHubUser>()) { _, _, dev ->
        currentCount = 0
        totalCountPerPage = dev.size
    }

    override var currentCount = 0
        private set

    override var totalCountPerPage = 0
        private set

    override var currentPage = 1
        private set

    companion object {
        fun create() = GitHubStateImpl(Retrofit.Builder()
                .baseUrl(NetworkData.baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(GitHubService::class.java))
    }

    override fun getGitHubUsersFromApi(): Single<Response>? =
            retrofit.searchDevelopers(
                    NetworkData.query,
                    currentPage++,
                    NetworkData.type
            )


    fun loadAlreadyFetchedUsers(): MutableList<GitHubUser> {
        val offset = calculateOffsetIfLessThenDefault()
        val newList = currentDevelopers.subList(currentCount, currentCount + offset)
        currentCount += offset
        return newList
    }

    fun setDevelopers(it: Response?) {
        val developers = it?.items?.asSequence()?.sortedBy { it.login }?.toMutableList()
        if (developers != null) {
            currentDevelopers = developers
        }
    }

    private fun calculateOffsetIfLessThenDefault(): Int {
        return if (totalCountPerPage - currentCount < USERS_TO_LOAD_AT_ONCE) {
            totalCountPerPage - currentCount
        } else {
            USERS_TO_LOAD_AT_ONCE
        }
    }

    override fun getGitHubUserInfo(username: String): Single<GitHubUser>? =
            retrofit.getDeveloper(username)

}
