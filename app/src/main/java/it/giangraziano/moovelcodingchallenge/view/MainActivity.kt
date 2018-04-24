package it.giangraziano.moovelcodingchallenge.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import it.giangraziano.moovelcodingchallenge.R
import it.giangraziano.moovelcodingchallenge.adapters.DevelopersListAdapter
import it.giangraziano.moovelcodingchallenge.extensions.setColumnsLayout
import it.giangraziano.moovelcodingchallenge.model.GitHubUser
import it.giangraziano.moovelcodingchallenge.presenter.MainActivityPresenter
import it.giangraziano.moovelcodingchallenge.presenter.MainActivityPresenterImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    companion object {
        private const val TAG = "MAIN_ACTIVITY"
    }

    private val developerListRecyclerView: RecyclerView by lazy {
        items_list.setColumnsLayout(this, false)
        items_list.adapter = DevelopersListAdapter()
        items_list
    }

    private var mainActivityPresenter: MainActivityPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainActivityPresenter = MainActivityPresenterImpl(this)
        mainActivityPresenter?.onResume()
    }

    override fun showProgress() {
        progress_bar.visibility = ProgressBar.VISIBLE
        error_text_message.visibility = TextView.GONE
        developerListRecyclerView.visibility = RecyclerView.GONE
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
    }

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun setData(dataList: MutableList<GitHubUser>) {
        (developerListRecyclerView.adapter as DevelopersListAdapter).addData(dataList)
    }
}
