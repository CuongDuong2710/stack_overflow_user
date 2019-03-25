package cuongduong.developer.android.stackoverflow.ui.detail

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import cuongduong.developer.android.stackoverflow.R
import kotlinx.android.synthetic.main.user_detail_reputation_fragment.*

class UserDetailReputationFragment : Fragment() {

    companion object {
        fun newInstance() = UserDetailReputationFragment()
    }

    private lateinit var viewModel: UserDetailReputationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.user_detail_reputation_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserDetailReputationViewModel::class.java)

        val safeArgs = arguments?.let { UserDetailReputationFragmentArgs.fromBundle(it) }
        val userId = safeArgs?.userId
    }

}
