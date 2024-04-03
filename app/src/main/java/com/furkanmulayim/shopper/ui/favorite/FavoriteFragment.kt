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
        adapter = FavoriteAdapter(mcontext, viewModel.favorite)
        binding.FavoriteRcyc.layoutManager = LinearLayoutManager(mcontext)
        binding.FavoriteRcyc.adapter = adapter
    }

}