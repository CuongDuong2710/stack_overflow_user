package cuongduong.developer.android.stackoverflow.ui.home

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import cuongduong.developer.android.stackoverflow.R
import cuongduong.developer.android.stackoverflow.data.StackExchangeApiServices
import cuongduong.developer.android.stackoverflow.data.network.ConnectivityInterceptorImp
import cuongduong.developer.android.stackoverflow.data.network.StackExchangeNetworkDataSourceImpl
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

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
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        val apiService = StackExchangeApiServices(ConnectivityInterceptorImp(this.context!!))
        val stackExchangeNetworkDataSource = StackExchangeNetworkDataSourceImpl(apiService)

        stackExchangeNetworkDataSource.downloadUserList.observe(this, Observer {
            textView.text = it.items[0].displayName
        })

        GlobalScope.launch(Dispatchers.Main) {
            stackExchangeNetworkDataSource.fetchUserList(1, 30)
        }

    }

}
