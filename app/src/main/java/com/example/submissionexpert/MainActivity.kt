package com.example.submissionexpert

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.submissionexpert.databinding.ActivityMainBinding
import com.example.submissionexpert.home.HomeFragment

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment, HomeFragment())
                .commit()
            supportActionBar?.title = getString(R.string.app_name)
        }

        binding.appBarMain.materialToolbar.setNavigationOnClickListener {
            binding.drawerLayout.open()
        }

        binding.navView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            binding.drawerLayout.close()
            true
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}