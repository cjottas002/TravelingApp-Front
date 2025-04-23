package es.travelworld.traveling.ui.home

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import es.travelworld.traveling.R
import es.travelworld.traveling.ui.home.adapters.ViewPagerAdapter
import es.travelworld.traveling.databinding.ActivityHomeBinding
import es.travelworld.traveling.ui.home.fragments.HomeTabFragment
import es.travelworld.traveling.ui.home.fragments.TransportTabFragment
import es.travelworld.traveling.ui.home.fragments.HotelTabFragment
import es.travelworld.traveling.ui.home.fragments.TabFragment3
import es.travelworld.traveling.ui.rentcar.RentCarFragment

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val requestPermissionsLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            var allGranted = true
            permissions.entries.forEach {
                if (!it.value) allGranted = false
            }

            if (allGranted) {
                setupUI()
            } else {
                Toast.makeText(this, "Permisos necesarios no concedidos. Cerrando la aplicación.", Toast.LENGTH_LONG).show()
                finish()
            }
        }

    private fun requestNecessaryPermissions() {
        val requiredPermissions = arrayOf(Manifest.permission.ACCESS_FINE_LOCATION)

        val permissionsToRequest = requiredPermissions.filter {
            ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
        }

        if (permissionsToRequest.isNotEmpty()) {
            requestPermissionsLauncher.launch(permissionsToRequest.toTypedArray())
        } else {
            setupUI()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolbar()
        requestNecessaryPermissions()
    }

    private fun setupUI() {

        val homeTabFragment = HomeTabFragment().apply {
            arguments = Bundle().apply {
                putString("USERNAME", intent.getStringExtra("USERNAME"))
                putString("PASSWORD", intent.getStringExtra("PASSWORD"))
            }
        }

        val fragments = listOf(
            homeTabFragment,
            TransportTabFragment(),
            HotelTabFragment(),
            TabFragment3()
        )

        val adapter = ViewPagerAdapter(this, fragments)
        binding.viewPagerHome.adapter = adapter

        TabLayoutMediator(binding.tabLayoutHome, binding.viewPagerHome) { tab, position ->
            when (position + 1) {
                1 -> tab.setIcon(R.drawable.common_ic_camera)
                2 -> tab.setIcon(R.drawable.home_ic_heart)
                3 -> tab.setIcon(R.drawable.home_ic_tent)
                4 -> tab.setIcon(R.drawable.home_ic_smile)
            }
        }.attach()
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbarHome.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        supportActionBar?.setHomeButtonEnabled(false)
        supportActionBar?.title = "Home"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_eurodisney -> {
                openEuroDisneyIntent()
                true
            }
            R.id.menu_rent_car -> {
                openRentCarFragment()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openEuroDisneyIntent() {
        val eurodisneyIntent = Intent(Intent.ACTION_VIEW, "https://www.disneylandparis.com/".toUri())
        startActivity(eurodisneyIntent)
    }

    private fun openRentCarFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.root_container, RentCarFragment())
            .addToBackStack(null)
            .commit()
    }
}
