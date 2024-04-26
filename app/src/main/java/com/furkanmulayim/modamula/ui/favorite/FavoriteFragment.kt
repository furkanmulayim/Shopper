package com.furkanmulayim.modamula.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.furkanmulayim.modamula.R
import com.furkanmulayim.modamula.base.BaseFragment
import com.furkanmulayim.modamula.data.model.Product
import com.furkanmulayim.modamula.databinding.FragmentFavoriteBinding
import com.furkanmulayim.modamula.utils.viewGone
import com.furkanmulayim.modamula.utils.viewMessage
import com.furkanmulayim.modamula.utils.viewVisible

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
        // adapter = FavoriteAdapter(viewModel.favorite, ::onClickItemDelete, ::onClickItem)
        //binding.FavoriteRcyc.layoutManager = LinearLayoutManager(mcontext)
        // binding.FavoriteRcyc.adapter = adapter
        // emptyAdapterControl()
    }

    private fun onClickItem(item: Product) {
        val bundle = Bundle().apply {
            putParcelable("ProductItem", item)
        }
        val act = FavoriteFragmentDirections.actionFavoriteFragmentToProductDetailFragment()
        navigateTo(actionId = act.actionId, bundle = bundle)
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