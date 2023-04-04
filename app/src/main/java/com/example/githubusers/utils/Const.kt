package com.example.githubusers.utils

import com.example.githubusers.domain.dto.GitHubUser

//API
const val BASE_URL_API_GIT_HUB_USERS = "https://api.github.com/"
const val BASE_URL_API_GIT_HUB_IMAGE = "https://img2.freepng.ru/"
const val END_POINT_API_GIT_HUB_USERS = "users"
const val END_POINT_API_GIT_HUB_IMAGE = "20190523/juu/kisspng-github-software-repository-computer-icons-email-5ce6e863973725.5475298415586366436194.jpg"

//Room
const val DATABASE_NAME = "GitHubUser.db"
const val DATABASE_HAS_NOT_CREATED = "Database has not been created."

const val DURATION_FADE_IN_GIT_HUB_USERS = 2000L
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

const val MESSAGE_ERROR_PERMISSION_TO_READ = "There is no permission to read the file."
const val MESSAGE_ERROR_READ_JPG = "The file is not being read"
const val MESSAGE_ERROR_CONVERSION_TO_PNG = "File conversion and saving error"
const val MESSAGE_ERROR_FILE_NOT_FOUND = "File not found"
const val GIT_HUB_IMAGE_JPG = "git_hub.jpg"
const val GIT_HUB_IMAGE_PNG = "git_hub.png"
const val MESSAGE_FOR_SAVED_SUCCESSFULLY= "The file was saved successfully"
const val MESSAGE_PROCESS_OF_SAVING_IN_PNG= "File in the process of saving in png"
