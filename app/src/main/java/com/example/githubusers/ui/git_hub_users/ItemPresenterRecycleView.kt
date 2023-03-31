package com.example.githubusers.ui.git_hub_users

interface ItemPresenterRecycleView <itemRecycleView: ItemRecycleView, entity: Any> {
    val entityList:MutableList<entity>
    fun getCount():Int
    fun getId(itemPosition:Int):Int
    fun bindView(itemView: itemRecycleView)
}