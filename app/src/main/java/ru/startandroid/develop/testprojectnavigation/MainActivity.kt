package ru.startandroid.develop.testprojectnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import ru.startandroid.develop.testprojectnavigation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
private lateinit var navController: NavController
private lateinit var binding : ActivityMainBinding
private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        explain_binding() //!.
        val view = binding.root
        setContentView(view)

        //? объявляем host для фрагментов и находим navController
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
        navController = navHostFragment.findNavController()

        explain_AppBar() //!.
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.fragmentFound, R.id.fragmentLost,
            R.id.fragmentMessages, R.id.fragmentProfile,
            R.id.fragmentLogin)
        )


        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
        explain_setSupportActionBar() //!.

        //? тут пока недоделано, это должен был быть drawerLayout т.е. бургер, но пока не понятно.
        binding.navView.setupWithNavController(navController)
    }

    //? функция отвечает за работу backButton. По сути без нее нажатие на backButton на верху не должно давать никакого эффекта.
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}







