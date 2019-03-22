package cuongduong.developer.android.stackoverflow.data.network

import androidx.lifecycle.LiveData
import cuongduong.developer.android.stackoverflow.data.network.response.UserListResponse

interface StackExchangeNetworkDataSource {
    val downloadUserList: LiveData<UserListResponse>

    suspend fun fetchUserList(
        page: Int,
        pageSize: Int
    )
}