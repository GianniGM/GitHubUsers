package it.giangraziano.moovelcodingchallenge.model

data class Response(
        val total_count: Long?,
        val incomplete_results: Boolean?,
        val items: MutableList<GitHubUser>?
)

data class GitHubUser(
        val login: String? = "no login",
        val id: Int? = 123,
        val avatar_url: String? = "no avatar",
        val url: String? = "no url",
        val html_url: String = "no url",
        val type: String = "no type",
        val followers: Long? = 0,
        val created_at: String? = "no data",
        val updated_at: String?  = "no data",
        val email: String?  = "no email",
        val bio: String?  = "no bio"
)
