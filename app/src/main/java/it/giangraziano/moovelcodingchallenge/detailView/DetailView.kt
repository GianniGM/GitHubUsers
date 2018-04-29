package it.giangraziano.moovelcodingchallenge.detailView

import it.giangraziano.moovelcodingchallenge.model.GitHubUser

interface DetailView {
    fun showToast(message: String)
    fun setView(user: GitHubUser?)
}