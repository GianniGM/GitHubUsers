package it.giangraziano.moovelcodingchallenge.mainView

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import it.giangraziano.moovelcodingchallenge.R
import it.giangraziano.moovelcodingchallenge.mainView.adapters.DevelopersListAdapter
import it.giangraziano.moovelcodingchallenge.extensions.onScrollToEnd
import it.giangraziano.moovelcodingchallenge.extensions.setColumnsLayout
import it.giangraziano.moovelcodingchallenge.model.GitHubUser
import it.giangraziano.moovelcodingchallenge.model.GitHubStateImpl
import it.giangraziano.moovelcodingchallenge.detailView.UserDetailActivity
import kotlinx.android.synthetic.main.activity_main.*

const val EXTRA_USER_LOGIN = "extra_user_id"

class MainActivity : AppCompatActivity(), MainView {

    companion object {
        private const val TAG = "MAIN_ACTIVITY"
    }

    private val developerListRecyclerView: RecyclerView by lazy {
        items_list.setColumnsLayout(this, false)
        items_list.adapter = DevelopersListAdapter { item -> onItemClick(item) }
        items_list
    }

    private lateinit var mainActivityPresenter: MainActivityPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivityPresenter = MainActivityPresenterImpl(
                this,
                GitHubStateImpl.create()
        )

        mainActivityPresenter.initialLoading()

        developerListRecyclerView.onScrollToEnd(false) {
            mainActivityPresenter.loadMore()

        }

        button_refresh.setOnClickListener {
            button_refresh.visibility = Button.GONE
            mainActivityPresenter.initialLoading()
        }
    }


    private fun onItemClick(item: GitHubUser) {
        val intent = Intent(this, UserDetailActivity::class.java)
        intent.putExtra(EXTRA_USER_LOGIN, item.login)
        startActivity(intent)
    }

    override fun showProgress() {
        progress_bar.visibility = ProgressBar.VISIBLE
        error_text_message.visibility = TextView.GONE
        developerListRecyclerView.visibility = RecyclerView.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivityPresenter.dispose()
    }

    override fun hideProgress(loadingSuccess: Boolean) {
        progress_bar.visibility = ProgressBar.GONE
        developerListRecyclerView.visibility = if (loadingSuccess)
            RecyclerView.VISIBLE
        else
            RecyclerView.GONE

        error_text_message.visibility = if (loadingSuccess)
            TextView.GONE
        else
            TextView.VISIBLE

        button_refresh.visibility = if (loadingSuccess)
            Button.GONE
        else
            Button.VISIBLE
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun addData(dataList: MutableList<GitHubUser>) {
        (developerListRecyclerView.adapter as DevelopersListAdapter).addData(dataList)
    }
}
