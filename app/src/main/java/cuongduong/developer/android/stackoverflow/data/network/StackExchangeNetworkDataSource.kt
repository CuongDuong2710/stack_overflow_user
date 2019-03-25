package cuongduong.developer.android.stackoverflow.data.network

import androidx.lifecycle.LiveData
import cuongduong.developer.android.stackoverflow.data.network.response.UserReputationResponse
import cuongduong.developer.android.stackoverflow.data.network.response.UserListResponse

interface StackExchangeNetworkDataSource {
    val downloadUserList: LiveData<UserListResponse>
    val downloadUserReputationList: LiveData<UserReputationResponse>

    suspend fun fetchUserList(
        page: Int,
        pageSize: Int
    )

    suspend fun fetchUserReputation(
        page: Int,
        pageSize: Int
    )
}