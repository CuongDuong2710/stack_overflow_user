package cuongduong.developer.android.stackoverflow.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cuongduong.developer.android.stackoverflow.data.db.entity.ReputationItem

@Dao
interface UserReputationListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(reputationList: List<ReputationItem>)

    @Query("select * from reputation where userId = :userId")
    fun getUserReputation(userId: String): LiveData<List<ReputationItem>>
}