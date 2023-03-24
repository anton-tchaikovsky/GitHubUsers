package com.example.githubusers.utils

import com.example.githubusers.domain.dto.GitHubUser

//API
const val BASE_URL_API_GIT_HUB_USERS = "https://api.github.com/"
const val BASE_URL_API_GIT_HUB_IMAGE = "https://img2.freepng.ru/"
const val END_POINT_API_GIT_HUB_USERS = "users"
const val END_POINT_API_GIT_HUB_IMAGE = "20190523/juu/kisspng-github-software-repository-computer-icons-email-5ce6e863973725.5475298415586366436194.jpg"
const val GIT_HUB_IMAGE = "git_hub.jpg"

const val DURATION_FADE_IN_GIT_HUB_USERS = 2000L
val DEFAULT_GIT_HAB_USERS_LIST = listOf(
    GitHubUser(1, "mojombo", "https://avatars.githubusercontent.com/u/1?v=4"),
    GitHubUser(2, "defunkt", "https://avatars.githubusercontent.com/u/2?v=4"),
    GitHubUser(3, "pjhyett", "https://avatars.githubusercontent.com/u/3?v=4"),
    GitHubUser(4, "wycats", "https://avatars.githubusercontent.com/u/4?v=4"),
    GitHubUser(5, "ezmobius", "https://avatars.githubusercontent.com/u/5?v=4")
)