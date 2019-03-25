package cuongduong.developer.android.stackoverflow.ui.detail

import androidx.lifecycle.ViewModel;
import cuongduong.developer.android.stackoverflow.data.repository.StackExchangeRepository
import cuongduong.developer.android.stackoverflow.internal.lazyDeferred

class UserReputationViewModel(
    private val userId: String,
    private val stackExchangeRepository: StackExchangeRepository
) : ViewModel() {
    val userReputationList by lazyDeferred {
        stackExchangeRepository.getUserReputationList(userId)
    }
}
