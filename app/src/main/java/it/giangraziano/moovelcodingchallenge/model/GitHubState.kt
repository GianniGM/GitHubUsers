package it.giangraziano.moovelcodingchallenge.model

interface GitHubState {

    val currentCount: Int

    val totalCountPerPage: Int

    val currentPage: Int

}