package cuongduong.developer.android.stackoverflow.ui.detail

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
import cuongduong.developer.android.stackoverflow.data.db.entity.ReputationItem
import cuongduong.developer.android.stackoverflow.internal.UserNotFoundException
import cuongduong.developer.android.stackoverflow.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.user_reputation_list_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.factory

class UserReputationFragment : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein()

    private val viewModelFactory
            : ((String) -> UserReputationViewModelFactory) by factory()

    private lateinit var viewModel: UserReputationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_reputation_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val safeArgs = arguments?.let { UserReputationFragmentArgs.fromBundle(it) }
        val userId = safeArgs?.userId ?: throw UserNotFoundException()

        viewModel = ViewModelProviders.of(this, viewModelFactory(userId.toString())).get(UserReputationViewModel::class.java)
        bindUI()
    }

    private fun bindUI() = launch(Dispatchers.Main) {
        val userReputationList = viewModel.userReputationList.await()
        userReputationList.observe(this@UserReputationFragment, Observer { reputationEntries ->
            if (reputationEntries == null) return@Observer

            group_reputation_loading.visibility = View.GONE
            initRecycleView(reputationEntries.toUserReputationItem())
        })
    }

    private fun List<ReputationItem>.toUserReputationItem(): List<UserReputationItem> {
        return this.map {
            UserReputationItem(it)
        }
    }

    private fun initRecycleView(items: List<UserReputationItem>) {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(items)
        }

        recyclerView_reputation.apply {
            layoutManager = LinearLayoutManager(this@UserReputationFragment.context)
            adapter = groupAdapter
        }
    }

}
