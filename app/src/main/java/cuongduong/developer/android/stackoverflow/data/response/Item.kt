package cuongduong.developer.android.stackoverflow.data.response

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("badge_counts")
    val badgeCounts: BadgeCounts,
    @SerializedName("account_id")
    val accountId: Int,
    @SerializedName("is_employee")
    val isEmployee: Boolean,
    @SerializedName("last_modified_date")
    val lastModifiedDate: Int,
    @SerializedName("last_access_date")
    val lastAccessDate: Int,
    @SerializedName("reputation_change_year")
    val reputationChangeYear: Int,
    @SerializedName("reputation_change_quarter")
    val reputationChangeQuarter: Int,
    @SerializedName("reputation_change_month")
    val reputationChangeMonth: Int,
    @SerializedName("reputation_change_week")
    val reputationChangeWeek: Int,
    @SerializedName("reputation_change_day")
    val reputationChangeDay: Int,
    val reputation: Int,
    @SerializedName("creation_date")
    val creationDate: Int,
    @SerializedName("user_type")
    val userType: String,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("accept_rate")
    val acceptRate: Int,
    val location: String,
    @SerializedName("website_url")
    val websiteUrl: String,
    val link: String,
    @SerializedName("profile_image")
    val profileImage: String,
    @SerializedName("display_name")
    val displayName: String
)