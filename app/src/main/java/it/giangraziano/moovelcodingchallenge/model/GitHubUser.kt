package it.giangraziano.moovelcodingchallenge.model

data class GitHubUser(
        val login: String?,
        val id: Int?,
        val avatar_url: String?,
        val url: String?,
        val html_url: String,
        val type: String
)
