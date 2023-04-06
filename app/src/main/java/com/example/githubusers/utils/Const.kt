package com.example.githubusers.utils

import com.example.githubusers.domain.dto.GitHubUser

const val DURATION_FADE_IN = 2000L
const val DURATION_SAVE_GIT_HUB_IMAGE_PNG = 5000L

val DEFAULT_GIT_HAB_USERS_LIST = listOf(
    GitHubUser(
        1,
        "mojombo",
        "https://avatars.githubusercontent.com/u/1?v=4",
        "https://api.github.com/users/mojombo/repos"
    ),
    GitHubUser(
        2,
        "defunkt",
        "https://avatars.githubusercontent.com/u/2?v=4",
        "https://api.github.com/users/defunkt/repos"
    ),
    GitHubUser(
        3,
        "pjhyett",
        "https://avatars.githubusercontent.com/u/3?v=4",
        "https://api.github.com/users/pjhyett/repos"
    ),
    GitHubUser(
        4,
        "wycats",
        "https://avatars.githubusercontent.com/u/4?v=4",
        "https://api.github.com/users/wycats/repos"
    ),
    GitHubUser(
        5,
        "ezmobius",
        "https://avatars.githubusercontent.com/u/5?v=4",
        "https://api.github.com/users/ezmobius/repos"
    )
)
