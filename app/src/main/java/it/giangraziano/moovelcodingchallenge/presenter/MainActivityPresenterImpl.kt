package it.giangraziano.moovelcodingchallenge.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import it.giangraziano.moovelcodingchallenge.model.GitHubUser
import it.giangraziano.moovelcodingchallenge.view.MainView
import it.giangraziano.moovelcodingchallenge.network.GitHubNetwork
import kotlin.properties.Delegates

const val USERS_TO_LOAD_AT_ONCE = 10

class MainActivityPresenterImpl(private val mainView: MainView) : MainActivityPresenter {


    private val network = GitHubNetwork.create()
    private var obs: Disposable? = null
    private var currentPage = 1

    private var currentCount = 0
    private var totalCountPerPage = 0
    private var currentDevelopers by Delegates.observable(mutableListOf<GitHubUser>()) { _, _, dev ->
        currentCount = 0
        totalCountPerPage = dev.size
        loadUsers()
    }

    override fun onResume() {
        mainView.showProgress()
        loadUsers()
    }

    override fun onScrollToEnd() {
        loadUsers()
    }

    override fun onRecyclerViewScrollToEnd() {
        loadUsers()
    }

    private fun loadUsers() {

        val offset = calculateOffsetIfLessThenDefault()

        if (currentCount < totalCountPerPage) {
            val newList = currentDevelopers.subList(currentCount, currentCount + offset)
            mainView.addData(newList)
            currentCount += USERS_TO_LOAD_AT_ONCE
        } else {
            serve()
        }
    }

    private fun calculateOffsetIfLessThenDefault(): Int {
        return if (totalCountPerPage - currentCount < USERS_TO_LOAD_AT_ONCE) {
            totalCountPerPage - currentCount
        } else {
            USERS_TO_LOAD_AT_ONCE
        }
    }

    private fun serve() {
        this.obs = network.getGitHubUsersFromApi(currentPage++)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    val developers = it.items?.asSequence()?.sortedBy { it.login }?.toMutableList()
                    if (developers != null) {
                        currentDevelopers = developers
                    }
                    mainView.hideProgress(true)
                }, {
                    mainView.hideProgress(false)
                    mainView.showToast(it.localizedMessage.toString())
                })
    }

    override fun onDestroy() {
        this.obs?.dispose()
    }

}