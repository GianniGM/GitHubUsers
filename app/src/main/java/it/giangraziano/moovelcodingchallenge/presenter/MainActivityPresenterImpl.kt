package it.giangraziano.moovelcodingchallenge.presenter

import io.reactivex.disposables.Disposable
import it.giangraziano.moovelcodingchallenge.model.GitHubStateImpl
import it.giangraziano.moovelcodingchallenge.mainView.MainView

const val USERS_TO_LOAD_AT_ONCE = 10

class MainActivityPresenterImpl(
        private val mainView: MainView,
        private val state: GitHubStateImpl
) : MainActivityPresenter {

    var disposable: Disposable? = null

    override fun onResume() {
        mainView.showProgress()
        loadUsers()
    }

    override fun onScrollToEnd() {
        loadUsers()
    }

    override fun onDestroy() {
        this.disposable?.dispose()
    }

    private fun loadUsers() {
        if (state.currentCount < state.totalCountPerPage)
            mainView.addData(state.loadAlreadyFetchedUsers())
        else
            this.disposable = state.getGitHubUsersFromApi()?.subscribe({
                state.setDevelopers(it)
                mainView.addData(state.loadAlreadyFetchedUsers())
                mainView.hideProgress(true)
            }, {
                mainView.hideProgress(false)
                mainView.showToast(it.localizedMessage.toString())
            })
    }
}