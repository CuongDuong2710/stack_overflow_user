package cuongduong.developer.android.stackoverflow.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "reputation")
data class ReputationItem(
    @SerializedName("reputation_history_type")
    val reputationHistoryType: String,
    @SerializedName("reputation_change")
    val reputationChange: Int,
    @SerializedName("post_id")
    val postId: Int,
    @SerializedName("creation_date")
    val creationDate: Long,
    @SerializedName("user_id")
    val userId: Int
){
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
