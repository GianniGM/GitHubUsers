package it.giangraziano.moovelcodingchallenge.detailView

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import it.giangraziano.moovelcodingchallenge.model.GitHubStateImpl

class DetailActivityPresenterImpl(
        private val detailView: DetailView,
        private val userLogin: String
) : DetailActivityPresenter {

    private val state = GitHubStateImpl.create()
    private var disposable: Disposable? = null

    override fun load() {
        serve()
    }

    override fun dispose() {
        disposable?.dispose()
    }

    private fun serve() {
        this.disposable = state.getGitHubUserInfo(userLogin)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    detailView.setView(it)
                }, {
                    detailView.showToast(it.localizedMessage.toString())
                })
    }
}