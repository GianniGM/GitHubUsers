package it.giangraziano.moovelcodingchallenge.network

class NetworkData {
    companion object {
        //https://api.github.com/search/users?page=0&q=+language:java&type=user
        const val query="language:java"
        const val type = "user"
        const val baseUrl="https://api.github.com"
    }
}