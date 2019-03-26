package cuongduong.developer.android.stackoverflow.ui.bookmark

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.ViewHolder

import cuongduong.developer.android.stackoverflow.R
import cuongduong.developer.android.stackoverflow.data.db.entity.BookmarkItem
import cuongduong.developer.android.stackoverflow.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.bookmark_list_fragment.*
import kotlinx.android.synthetic.main.user_list_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class BookmarkFragment : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein()

    private val viewModelFactory: BookmarkViewModelFactory by instance()

    private lateinit var viewModel: BookmarkViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bookmark_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(BookmarkViewModel::class.java)

        bindUI()
    }

    private fun bindUI() = launch(Dispatchers.Main) {
        viewModel.insertBookmarkItem.await()

        val bookmarkList = viewModel.bookmarkList.await()
        bookmarkList.observe(this@BookmarkFragment, Observer { bookmarkEntries ->
            if (bookmarkEntries == null) return@Observer

            group_bookmark_loading.visibility = View.GONE
            initRecycleView(bookmarkEntries.toBookmarkListItem())
        })
    }

    private fun List<BookmarkItem>.toBookmarkListItem(): List<BookmarkListItem> {
        return this.map {
            BookmarkListItem(it, {item, favorite ->  })
        }
    }

    private fun initRecycleView(items: List<BookmarkListItem>) {
        val groupAdapter= GroupAdapter<ViewHolder>().apply {
            addAll(items)
        }

        recyclerView_bookmark.apply {
            layoutManager = LinearLayoutManager(this@BookmarkFragment.context)
            adapter = groupAdapter
        }
    }

}
