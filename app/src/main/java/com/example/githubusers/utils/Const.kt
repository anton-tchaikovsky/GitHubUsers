package com.example.githubusers.utils

import com.example.githubusers.domain.dto.GitHubUser

//API
const val BASE_URL_API_GIT_HUB_USERS = "https://api.github.com/"
const val END_POINT_API_GIT_HUB_USERS = "users"

const val DURATION_FADE_IN_GIT_HUB_USERS = 2000L
val DEFAULT_GIT_HAB_USERS_LIST = listOf(
    GitHubUser(1, "mojombo", "https://avatars.githubusercontent.com/u/1?v=4"),
    GitHubUser(2, "defunkt", "https://avatars.githubusercontent.com/u/2?v=4"),
    GitHubUser(3, "pjhyett", "https://avatars.githubusercontent.com/u/3?v=4")
)