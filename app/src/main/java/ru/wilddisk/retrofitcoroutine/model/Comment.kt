package ru.wilddisk.retrofitcoroutine.model

import com.google.gson.annotations.SerializedName

/**
 * Serialize model
 */
class Comment(
    @SerializedName("postId")
    val mPostId: Long,
    @SerializedName("id")
    val mId: Long,
    @SerializedName("name")
    val mName: String,
    @SerializedName("email")
    val mEmail: String,
    @SerializedName("body")
    val mBody: String
)