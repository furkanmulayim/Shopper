package com.furkanmulayim.modamula.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.furkanmulayim.modamula.R
import com.furkanmulayim.modamula.base.BaseFragment
import com.furkanmulayim.modamula.data.model.Product
import com.furkanmulayim.modamula.databinding.FragmentFavoriteBinding
import com.furkanmulayim.modamula.utils.viewGone
import com.furkanmulayim.modamula.utils.viewVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        viewModel.getDatas()
        initSetup()
        observeData()
    }

    private fun initSetup() {
        viewGone(binding.toolBar.toolbarStart)
        viewGone(binding.toolBar.toolbarEnd)
        binding.toolBar.toolbarTitle.text = getString(R.string.favorie)
        initAdapter()
    }

    private fun observeData() {
        viewModel.productList.observe(viewLifecycleOwner) { fav ->
            fav?.let {
                if (fav.isNotEmpty()) {
                    viewGone(binding.emptyListPanel)
                    adapter.updateList(fav as ArrayList<Product>)
                } else {
                    viewGone(binding.FavoriteRcyc)
                    viewVisible(binding.emptyListPanel)
                }
            }
        }
    }

    private fun initAdapter() {
        adapter = FavoriteAdapter(
            arrayListOf(),
            ::onClickItemDelete,
            ::onClickItem
        )
        binding.FavoriteRcyc.layoutManager = LinearLayoutManager(mcontext)
        binding.FavoriteRcyc.adapter = adapter
        emptyAdapterControl()
    }

    private fun onClickItem(item: Product) {
        val bundle = Bundle().apply {
            putParcelable("ProductItem", item)
        }
        val act = FavoriteFragmentDirections.actionFavoriteFragmentToProductDetailFragment()
        navigateTo(actionId = act.actionId, bundle = bundle)
    }

    private fun onClickItemDelete(id: Int) {
        viewModel.deleteFav(id)
        val newlist = viewModel.productList.value?.filter { it.id != id }
        adapter.updateList(newlist as ArrayList<Product>)
        emptyAdapterControl()
    }

    private fun emptyAdapterControl() {
        if (adapter.itemCount == 0) {
            viewVisible(binding.emptyListPanel)
        } else {
            viewGone(binding.emptyListPanel)
        }
    }

    override fun onResume() {
        super.onResume()
        emptyAdapterControl()
    }
}