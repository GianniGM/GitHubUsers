package it.giangraziano.moovelcodingchallenge.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import it.giangraziano.moovelcodingchallenge.model.GitHubState
import it.giangraziano.moovelcodingchallenge.view.DetailView

class DetailActivityPresenterImpl(
        private val detailView: DetailView,
        private val userLogin: String
) : DetailActivityPresenter {

    private val network = GitHubState.create()
    private var disposable: Disposable? = null

    override fun onResume() {
        serve()
    }

    override fun onDestroy() {
        disposable?.dispose()
    }

    private fun serve() {
        this.disposable = network.getGitHubUserInfo(userLogin)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    detailView.setView(it)
                }, {
                    detailView.showToast(it.localizedMessage.toString())
                })
    }
}