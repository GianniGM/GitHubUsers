package it.giangraziano.moovelcodingchallenge.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import it.giangraziano.moovelcodingchallenge.network.GitHubNetwork
import it.giangraziano.moovelcodingchallenge.view.DetailView

class DetailActivityPresenterImpl(private val detailView: DetailView) : DetailActivityPresenter {

    private val network = GitHubNetwork.create()
    private var obs: Disposable? = null

    override fun onResume(username: String) {
        serve(username)
    }

    override fun onDestroy() {
        obs?.dispose()
    }

    private fun serve(username: String) {
        this.obs = network.getGitHubUserInfo(username)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    detailView.setView(it)
                }, {
                    detailView.showToast(it.localizedMessage.toString())
                })
    }
}