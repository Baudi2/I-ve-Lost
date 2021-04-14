package ru.startandroid.develop.testprojectnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import ru.startandroid.develop.testprojectnavigation.databinding.ActivityMainBinding
import ru.startandroid.develop.testprojectnavigation.utils.APP_ACTIVITY
import ru.startandroid.develop.testprojectnavigation.utils.explainAppBar
import ru.startandroid.develop.testprojectnavigation.utils.explainBinding
import ru.startandroid.develop.testprojectnavigation.utils.explainSetSupportActionBar

class MainActivity : AppCompatActivity() {
private lateinit var navController: NavController
private lateinit var binding : ActivityMainBinding
private lateinit var appBarConfiguration: AppBarConfiguration
lateinit var drawer: DrawerLayout
lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        explainBinding() //!.
        val view = binding.root
        setContentView(view)

        APP_ACTIVITY = this
        drawer = binding.drawerLayout
        toolbar = binding.toolbar

        //? объявляем host для фрагментов и находим navController
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
        navController = navHostFragment.findNavController()

        explainAppBar() //!.
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.fragmentFound, R.id.fragmentLost,
            R.id.fragmentMessages, R.id.fragmentProfile),
            drawer
        )

        setSupportActionBar(toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)
        explainSetSupportActionBar() //!.

        //? тут пока недоделано, это должен был быть drawerLayout т.е. бургер, но пока не понятно.
        binding.navView.setupWithNavController(navController)
    }

    //? функция отвечает за работу backButton. По сути без нее нажатие на backButton на верху не должно давать никакого эффекта.
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}







