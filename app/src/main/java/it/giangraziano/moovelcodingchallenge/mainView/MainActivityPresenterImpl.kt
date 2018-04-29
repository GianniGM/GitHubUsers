package it.giangraziano.moovelcodingchallenge.mainView

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import it.giangraziano.moovelcodingchallenge.model.GitHubStateImpl

const val USERS_TO_LOAD_AT_ONCE = 10

class MainActivityPresenterImpl(
        private val mainView: MainView,
        private val state: GitHubStateImpl
) : MainActivityPresenter {

    var disposable: Disposable? = null

    override fun initialLoading() {
        mainView.showProgress()
        loadUsers()
    }

    override fun loadMore() {
        loadUsers()
    }

    override fun dispose() {
        this.disposable?.dispose()
    }

    private fun loadUsers() {
        if (state.currentCount < state.totalCountPerPage)
            mainView.addData(state.loadAlreadyFetchedUsers())
        else
            this.disposable = state.getGitHubUsersFromApi()
                    ?.subscribeOn(Schedulers.io())
                    ?.observeOn(AndroidSchedulers.mainThread())
                    ?.subscribe({
                        state.setDevelopers(it)
                        mainView.addData(state.loadAlreadyFetchedUsers())
                        mainView.hideProgress(true)
                    }, {
                        mainView.hideProgress(false)
                        mainView.showToast(it.localizedMessage.toString())
                    })
    }
}