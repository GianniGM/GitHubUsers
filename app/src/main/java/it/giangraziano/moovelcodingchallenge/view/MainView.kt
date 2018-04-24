package it.giangraziano.moovelcodingchallenge.view


import it.giangraziano.moovelcodingchallenge.model.GitHubUser

interface MainView {
    fun showProgress()
    fun hideProgress(loadingSuccess: Boolean)
    fun setData(dataList: MutableList<GitHubUser>)
    fun showToast(message:String)
}