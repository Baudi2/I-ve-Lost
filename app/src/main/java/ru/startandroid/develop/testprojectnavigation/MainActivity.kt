package ru.startandroid.develop.testprojectnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.google.android.material.navigation.NavigationView
import ru.startandroid.develop.testprojectnavigation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
private lateinit var navController: NavController
private lateinit var binding : ActivityMainBinding
private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //? подключение binding для активити немного отличается от процесса подключение binding к фрагменту
        //? но код всегда одинаковый так что можно просто записать где-нибудь.
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //? объявляем host для фрагментов и находим navController
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_main) as NavHostFragment
        navController = navHostFragment.findNavController()

        //? appBarConfiguration нужен чтобы отметить высокоуровневые фрагменты т.е. те фрагменты после перехода на которые
        //? не будет на верху показываться backButton. appBarConfiguration нужно передать в setupActionBarWithNavController
        //? чтобы он заробатал.
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.fragmentFound, R.id.fragmentLost,
            R.id.fragmentMessages, R.id.fragmentProfile)
        )

        //? подключаем верхний тулбар так чтобы он показывался во всех фрагментах.То что мы настраиваем тут имеет эффект на все
        //? фрагменты поэтому мы объявили bottomNavigation напрямую в фрагментах т.к. он нам не нужен во всех фрагментах
        setSupportActionBar(binding.toolbar)
        setupActionBarWithNavController(navController, appBarConfiguration)

        //? тут пока недоделано, это должен был быть drawerLayout т.е. бургер, но пока не понятно.
        binding.navView.setupWithNavController(navController)
    }

    //? функция отвечает за работу backButton. По сути без нее нажатие на backButton на верху не должно давать никакого эффекта.
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    //TODO: баги с навигацией, при переходе на другой высокоуровневый фрагмент после
// TODO: нажатия кнопки назад не выводит из приложения а кидает на FragmentLost
}







