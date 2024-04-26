package com.furkanmulayim.modamula.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.furkanmulayim.modamula.R
import com.furkanmulayim.modamula.databinding.ActivityMainBinding
import com.furkanmulayim.modamula.utils.viewGone
import com.furkanmulayim.modamula.utils.viewVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setBottomNavBar()
    }

    private fun setBottomNavBar() {
        val navController: NavController = findNavController(R.id.fragmentContainerView)
        binding.bottomNavMenu.setupWithNavController(navController)
        // Giris Yapılmadan Bottom Nav Menü Görünmeyecek
        navController.addOnDestinationChangedListener { _, destination, _ ->
            val f = destination.id
            if (f == R.id.homeFragment || f == R.id.categoryFragment || f == R.id.favoriteFragment || f == R.id.shopFragment) viewVisible(
                binding.coordinatorLayout
            )
            else if (f == R.id.splashFragment) viewGone(binding.coordinatorLayout)
            else if (f == R.id.welcomeFragment) viewGone(binding.coordinatorLayout)
            else viewGone(binding.coordinatorLayout)
        }
    }


}
