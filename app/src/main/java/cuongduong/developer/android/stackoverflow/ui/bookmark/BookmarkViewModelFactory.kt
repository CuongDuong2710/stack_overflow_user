package cuongduong.developer.android.stackoverflow.ui.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import cuongduong.developer.android.stackoverflow.data.repository.StackExchangeRepository

class BookmarkViewModelFactory(
    private val stackExchangeRepository: StackExchangeRepository
): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return BookmarkViewModel(stackExchangeRepository) as T
    }
}