package cuongduong.developer.android.stackoverflow.data.repository

import androidx.lifecycle.LiveData
import cuongduong.developer.android.stackoverflow.data.db.entity.Item

interface StackExchangeRepository {
    suspend fun getUserList(): LiveData<List<Item>>
}