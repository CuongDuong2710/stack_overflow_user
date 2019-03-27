package cuongduong.developer.android.stackoverflow.ui.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder

import cuongduong.developer.android.stackoverflow.R
import cuongduong.developer.android.stackoverflow.data.db.entity.BookmarkItem
import cuongduong.developer.android.stackoverflow.data.db.entity.Item
import cuongduong.developer.android.stackoverflow.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.user_list_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class UserListFragment : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein() // closestKodein will get whatever in StackExchangeApplication

    private val viewModelFactory: UserListViewModelFactory by instance()

    private lateinit var viewModel: UserListViewModel

    private val handler = Handler()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserListViewModel::class.java)

        bindUI()
    }

    private fun bindUI() = launch(Dispatchers.Main) {
        val userList = viewModel.userList.await()
        // observer it from database, called every time there is some change in data
        userList.observe(this@UserListFragment, Observer { userEntries ->
            // if null, return Observer to observer database once again. When database has data, show displayName
            if (userEntries == null) return@Observer

            group_loading.visibility = View.GONE
            initRecycleView(userEntries.toUserListItem())
        })
    }

    // convert Item to UserListItem -> render each item on user list screen
    private fun List<Item>.toUserListItem(): List<UserListItem> {
        return this.map {
            UserListItem(this@UserListFragment.context, it) { item, favorite ->
                launch(Dispatchers.IO) {
                    viewModel.insert(convertItemToBookmarkItem(item.itemEntity))
                    handler.postDelayed({
                        item.setFavorite(favorite)
                        item.notifyChanged(FAVORITE)
                    }, 1000)
                }
            }
        }
    }

    private fun convertItemToBookmarkItem(item: Item): BookmarkItem {
        return BookmarkItem(item.lastAccessDate, item.reputation, item.userId, item.location, item.profileImage, item.displayName)
    }

    private fun initRecycleView(items: List<UserListItem>) {
        val groupAdapter= GroupAdapter<ViewHolder>().apply {
            addAll(items)
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@UserListFragment.context)
            adapter = groupAdapter
        }

        groupAdapter.setOnItemClickListener { item, view ->
            (item as? UserListItem)?.let {
                showReputationDetail(it.itemEntity.userId, view)
            }
        }
    }

    private fun showReputationDetail(userId: Int, view: View) {
        val detailReputation = UserListFragmentDirections.actionDetail(userId)
        Navigation.findNavController(view).navigate(detailReputation)
    }
}
