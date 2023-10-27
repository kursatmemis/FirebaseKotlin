package com.kursatmemis.firebasekotlin.view.home.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kursatmemis.firebasekotlin.R
import com.kursatmemis.firebasekotlin.databinding.ActivityMainBinding
import com.kursatmemis.firebasekotlin.view.authentication.activity.AuthenticationActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater : MenuInflater = menuInflater
        menuInflater.inflate(R.menu.main_activity_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.signOut -> {
                signOut()
                goToAuthenticationActivity()
            }
            R.id.updateProfile -> {
                val fragmentContainer = supportFragmentManager.findFragmentById(R.id.fragmentContainerView2) as NavHostFragment
                val navController = fragmentContainer.navController
                navController.navigate(R.id.action_homeFragment_to_updateProfileFragment)
                return true
            }
        }
        return true
    }

    private fun goToAuthenticationActivity() {
        val intent = Intent(this, AuthenticationActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun signOut() {
        Firebase.auth.signOut()
    }

}