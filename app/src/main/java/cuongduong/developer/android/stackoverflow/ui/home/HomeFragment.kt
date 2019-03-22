package cuongduong.developer.android.stackoverflow.ui.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import cuongduong.developer.android.stackoverflow.R
import cuongduong.developer.android.stackoverflow.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class HomeFragment : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein() // closestKodein will get whatever in StackExchangeApplication

    private val viewModelFactory: HomeViewModelFactory by instance()

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)

        bindUI()
    }

    private fun bindUI() = launch {
        val userList = viewModel.userList.await()
        // observer it from database, called every time there is some change in data
        userList.observe(this@HomeFragment, Observer {
            // if null, return Observer to observer database once again. When database has data, show displayName
            if (it == null) return@Observer

            textView.text = it[0].displayName // first call, database is null -> app crash -> check it == null
        })
    }

}
