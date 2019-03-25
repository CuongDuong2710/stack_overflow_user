package cuongduong.developer.android.stackoverflow.ui.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import cuongduong.developer.android.stackoverflow.R
import cuongduong.developer.android.stackoverflow.ui.base.ScopedFragment
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class UserReputationFragment : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein()

    private val viewModelFactory: UserReputationViewModelFactory by instance()

    private lateinit var viewModel: UserReputationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_detail_reputation_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserReputationViewModel::class.java)

        val safeArgs = arguments?.let { UserReputationFragmentArgs.fromBundle(it) }
        val userId = safeArgs?.userId
    }

}
