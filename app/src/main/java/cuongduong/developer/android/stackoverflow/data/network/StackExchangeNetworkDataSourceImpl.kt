package cuongduong.developer.android.stackoverflow.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cuongduong.developer.android.stackoverflow.data.StackExchangeApiServices
import cuongduong.developer.android.stackoverflow.data.network.response.UserListResponse
import cuongduong.developer.android.stackoverflow.internal.NoConnectivityException

class StackExchangeNetworkDataSourceImpl(
    private val stackExchangeApiServices: StackExchangeApiServices
) : StackExchangeNetworkDataSource {
    private val _downloadUserList = MutableLiveData<UserListResponse>()
    override val downloadUserList: LiveData<UserListResponse>
        get() = _downloadUserList //To change initializer of created properties use File | Settings | File Templates.

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
}