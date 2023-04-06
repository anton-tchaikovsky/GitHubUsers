package com.example.githubusers.ui.recycle_view

interface IItemPresenterRecycleView <itemRecycleView: IItemRecycleView, entity: Any> {
    val entityList:MutableList<entity>
    fun getCount():Int
    fun getId(itemPosition:Int):Int
    fun bindView(itemView: itemRecycleView)
}