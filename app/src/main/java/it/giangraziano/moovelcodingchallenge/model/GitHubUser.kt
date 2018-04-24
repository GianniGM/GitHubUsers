package it.giangraziano.moovelcodingchallenge.model

data class Response(
        val total_count: Long?,
        val incomplete_results: Boolean?,
        val items: MutableList<GitHubUser>?
)

data class GitHubUser(
        val login: String?,
        val id: Int?,
        val avatar_url: String?,
        val url: String?,
        val html_url: String,
        val type: String,
        val followers: Long?,
        val created_at: String?,
        val updated_at: String?,
        val email: String?
)
