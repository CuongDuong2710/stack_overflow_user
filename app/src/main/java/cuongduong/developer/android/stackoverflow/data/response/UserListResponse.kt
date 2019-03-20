package cuongduong.developer.android.stackoverflow.data.response

import com.google.gson.annotations.SerializedName

data class UserListResponse(
    val items: List<Item>,
    @SerializedName("has_more")
    val hasMore: Boolean,
    @SerializedName("quota_max")
    val quotaMax: Int,
    @SerializedName("quota_remaining")
    val quotaRemaining: Int
)