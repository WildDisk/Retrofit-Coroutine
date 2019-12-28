package ru.wilddisk.retrofitcoroutine.model

import com.google.gson.annotations.SerializedName

/**
 * Serialize model
 */
data class Post(
    @SerializedName("userId")
    val mUserId: Long,
    @SerializedName("id")
    val mId: Long,
    @SerializedName("title")
    val mTitle: String,
    @SerializedName("body")
    val mBody: String
)

//data class Post(
//    val userId: Long,
//    val id: Long,
//    val title: String,
//    val body: String
//): Serializable