package cuongduong.developer.android.stackoverflow.data.repository

import androidx.lifecycle.LiveData
import cuongduong.developer.android.stackoverflow.data.db.entity.Item
import cuongduong.developer.android.stackoverflow.data.db.entity.ReputationItem

interface StackExchangeRepository {
    suspend fun getUserList(): LiveData<List<Item>>
    suspend fun getUserReputationList(userId: String): LiveData<List<ReputationItem>>
}