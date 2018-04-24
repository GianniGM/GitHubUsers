package it.giangraziano.moovelcodingchallenge.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.squareup.picasso.Picasso
import it.giangraziano.moovelcodingchallenge.R
import it.giangraziano.moovelcodingchallenge.model.GitHubUser
import it.giangraziano.moovelcodingchallenge.presenter.DetailActivityPresenter
import it.giangraziano.moovelcodingchallenge.presenter.DetailActivityPresenterImpl
import kotlinx.android.synthetic.main.activity_user_detail.*

class UserDetailActivity : AppCompatActivity(), DetailView {

    private var detailActivityPresenter: DetailActivityPresenter? = null

    override fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        val userLogin = intent.extras.get(EXTRA_USER_LOGIN).toString()
        detailActivityPresenter = DetailActivityPresenterImpl(this, userLogin)
        detailActivityPresenter?.onResume()
    }

    override fun setView(user: GitHubUser?) {
        user_name.text = user?.login ?: getString(R.string.no_username)
        user_followers.text = user?.followers.toString()
                .plus(" ")
                .plus(getString(R.string.followers))

        user_email_address.text = user?.email ?: getString(R.string.no_email)
        bio_text.text = user?.bio ?: getString(R.string.no_bio)
        Picasso.get().load(user?.avatar_url).into(user_image_large)
        user_image_large.contentDescription = user?.login
    }

    override fun onDestroy() {
        super.onDestroy()
        detailActivityPresenter?.onDestroy()
    }
}
