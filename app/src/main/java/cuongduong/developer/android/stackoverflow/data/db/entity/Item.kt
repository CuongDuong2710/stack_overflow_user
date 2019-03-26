package cuongduong.developer.android.stackoverflow.data.db.entity

import androidx.annotation.Nullable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "item")
data class Item(
    @SerializedName("last_access_date")
    val lastAccessDate: Long,
    val reputation: Int,
    @SerializedName("user_id")
    @PrimaryKey
    val userId: Int,
    @Nullable
    val location: String? = null,
    @SerializedName("profile_image")
    val profileImage: String,
    @SerializedName("display_name")
    val displayName: String
)