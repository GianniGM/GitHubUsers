package it.giangraziano.moovelcodingchallenge.presenter

import it.giangraziano.moovelcodingchallenge.model.GitHubUser

interface DetailActivityPresenter {
    fun onResume(username: String)
    fun onDestroy()
}