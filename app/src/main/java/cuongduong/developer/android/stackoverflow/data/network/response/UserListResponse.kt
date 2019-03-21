package cuongduong.developer.android.stackoverflow.data.network.response

import com.google.gson.annotations.SerializedName
import cuongduong.developer.android.stackoverflow.data.db.entity.Item

data class UserListResponse(
    val items: List<Item>,
    @SerializedName("has_more")
    val hasMore: Boolean,
    @SerializedName("quota_max")
    val quotaMax: Int,
    @SerializedName("quota_remaining")
    val quotaRemaining: Int
)