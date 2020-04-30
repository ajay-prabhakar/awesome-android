package chromicle.recycleViewExample

import com.google.gson.annotations.SerializedName


/**
 *@author Chromicle
 */

data class Movie(
    val id: Int,
    val image: String,
    @SerializedName("is_new")
    val isNew: Int,
    val rating: String,
    @SerializedName("like_percent")
    val likePercent: Int,
    @SerializedName("vote_count")
    val voteCount: Int,
    val title: String,
    val language: String,
    val type: String
)