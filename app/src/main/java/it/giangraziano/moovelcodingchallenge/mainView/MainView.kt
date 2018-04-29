package it.giangraziano.moovelcodingchallenge.mainView


import it.giangraziano.moovelcodingchallenge.model.GitHubUser

interface MainView {
    fun showProgress()
    fun hideProgress(loadingSuccess: Boolean)
    fun addData(dataList: MutableList<GitHubUser>)
    fun showToast(message:String)
}