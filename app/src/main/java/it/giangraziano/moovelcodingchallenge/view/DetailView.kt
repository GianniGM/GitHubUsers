package it.giangraziano.moovelcodingchallenge.view

import it.giangraziano.moovelcodingchallenge.model.GitHubUser

interface DetailView {
    fun showToast(message: String)
    fun setView(user: GitHubUser?)
}