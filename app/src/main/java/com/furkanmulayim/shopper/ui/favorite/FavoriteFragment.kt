package com.furkanmulayim.shopper.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.furkanmulayim.shopper.R
import com.furkanmulayim.shopper.base.BaseFragment
import com.furkanmulayim.shopper.databinding.FragmentFavoriteBinding
import com.furkanmulayim.shopper.utils.viewGone
import com.furkanmulayim.shopper.utils.viewMessage
import com.furkanmulayim.shopper.utils.viewVisible

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>() {

    private lateinit var adapter: FavoriteAdapter
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFavoriteBinding {
        return FragmentFavoriteBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initSetup()
        initAdapter()
    }

    private fun initSetup() {
        viewGone(binding.toolBar.toolbarStart)
        viewGone(binding.toolBar.toolbarEnd)
        binding.toolBar.toolbarTitle.text = getString(R.string.favorie)
    }

    private fun initAdapter() {
        adapter = FavoriteAdapter(viewModel.favorite, ::onClickItemDelete)
        binding.FavoriteRcyc.layoutManager = LinearLayoutManager(mcontext)
        binding.FavoriteRcyc.adapter = adapter
        emptyAdapterControl()
    }

    private fun onClickItemDelete(id: Int) {
        viewMessage(mcontext, id.toString())
    }

    private fun emptyAdapterControl() {
        if (adapter.itemCount == 0) {
            viewVisible(binding.emptyListPanel)
        } else {
            viewGone(binding.emptyListPanel)
        }
    }
}