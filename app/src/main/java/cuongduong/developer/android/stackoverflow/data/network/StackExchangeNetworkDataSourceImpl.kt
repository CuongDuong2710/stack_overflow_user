package cuongduong.developer.android.stackoverflow.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cuongduong.developer.android.stackoverflow.data.StackExchangeApiServices
import cuongduong.developer.android.stackoverflow.data.network.response.UserReputationResponse
import cuongduong.developer.android.stackoverflow.data.network.response.UserListResponse
import cuongduong.developer.android.stackoverflow.internal.NoConnectivityException

class StackExchangeNetworkDataSourceImpl(
    private val stackExchangeApiServices: StackExchangeApiServices
) : StackExchangeNetworkDataSource {

    private val _downloadUserList = MutableLiveData<UserListResponse>()
    override val downloadUserList: LiveData<UserListResponse>
        get() = _downloadUserList

    private val _downloadUserReputationList = MutableLiveData<UserReputationResponse>()
    override val downloadUserReputationList: LiveData<UserReputationResponse>
        get() = _downloadUserReputationList

    override suspend fun fetchUserList(page: Int, pageSize: Int) {
        try {
            val fetchedUserList = stackExchangeApiServices
                .getUserList(page, pageSize)
                .await()
            _downloadUserList.postValue(fetchedUserList)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }

    override suspend fun fetchUserReputation(userId: String, page: Int, pageSize: Int) {
        try {
            val fetchUserReputationList = stackExchangeApiServices
                .getUserReputation(userId, page, pageSize)
                .await()
            _downloadUserReputationList.postValue(fetchUserReputationList)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }
}