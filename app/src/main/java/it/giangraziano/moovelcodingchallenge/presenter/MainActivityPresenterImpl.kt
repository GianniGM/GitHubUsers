package it.giangraziano.moovelcodingchallenge.presenter

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import it.giangraziano.moovelcodingchallenge.view.MainView
import it.giangraziano.moovelcodingchallenge.network.GitHubNetwork

class MainActivityPresenterImpl(private val mainView: MainView) : MainActivityPresenter{

    private val network = GitHubNetwork.create()
    private var obs: Disposable? = null

    override fun onRecyclerViewScrollToEnd() {
        serve()
    }

    override fun onResume() {
        mainView.showProgress()
        serve()
    }

    private fun serve() {
        this.obs = network.getGitHubUsersFromApi()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    mainView.hideProgress(true)
                    val developers = it.items?.asSequence()?.sortedBy { it.login }?.toMutableList()
                    if (developers != null) {
                        mainView.setData(developers)
                    }
                }, {
                    mainView.hideProgress(false)
                    mainView.showToast(it.localizedMessage.toString())
                })
    }

    override fun onDestroy() {
        this.obs?.dispose()
    }

}