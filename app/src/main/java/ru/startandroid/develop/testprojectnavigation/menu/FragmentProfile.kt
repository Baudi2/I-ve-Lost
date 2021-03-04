package ru.startandroid.develop.testprojectnavigation.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import ru.startandroid.develop.testprojectnavigation.R
import ru.startandroid.develop.testprojectnavigation.databinding.FragmentProfileBinding

class FragmentProfile : Fragment(R.layout.fragment_profile){

    //? переменная binding через которую мы можем безопасно получать доступ к view с xml layout который связан
    //? с этим фрагментом
    private lateinit var binding : FragmentProfileBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

            //? apply - делает так что все внутри попадет под binding т.е. не нужно каждый раз перед доступом ко view
            //? писать binding. просто сокращает код
        binding.apply {
            //? подключаем bottomNavigation к нашему фрагменту и задаем размер иконок
            bottomNavProfile.setupWithNavController(findNavController())
            bottomNavProfile.itemIconSize = 80

                //? ставим слушатель нажатий на наш floatingActionButton коротко "fab" и переходим на фрагмент добавить при нажатии.
            fabProfile.setOnClickListener {
                val action = FragmentProfileDirections.actionGlobalFragmentAdd()
                findNavController().navigate(action)
            }
        }
    }
}