package ru.startandroid.develop.testprojectnavigation.menu

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import ru.startandroid.develop.testprojectnavigation.R
import ru.startandroid.develop.testprojectnavigation.databinding.FragmentProfileBinding

class FragmentProfile : Fragment(R.layout.fragment_profile){

    //? переменная binding через которую мы можем безопасно получать доступ к view с xml layout который связан
    //? с этим фрагментом
    private lateinit var binding : FragmentProfileBinding
    //? тут мы принимаем аргумент из фрагменты регистрации или логина об успешной регистрации чтобы больше не показывать диалог
    private val args: FragmentProfileArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProfileBinding.bind(view)

            //? apply - делает так что все внутри попадет под binding т.е. не нужно каждый раз перед доступом ко view
            //? писать binding. просто сокращает код
        binding.apply {
            //? подключаем bottomNavigation к нашему фрагменту и задаем размер иконок
            bottomNavProfile.setupWithNavController(findNavController())
            bottomNavProfile.itemIconSize = 70

                //? ставим слушатель нажатий на наш floatingActionButton коротко "fab" и переходим на фрагмент добавить при нажатии.
            fabProfile.setOnClickListener {
                val action = FragmentProfileDirections.actionGlobalFragmentAddLostFind2()
                findNavController().navigate(action)
            }
        }
    }

    //? запускаем диалоговое окно каждый когда переходим сюда, в дальнейшем поменяем это певедние.
    override fun onStart() {
        super.onStart()
        if (!args.isRegistered) {
            registerDialog()
        }
    }

    //? Создаем алерт диалог который будет появляться если пользователь попытается зайти в
    //? фрагмент профеля без регистрации. Этот диалог должен перекинуть его в фрагмент для
    //? дальнейшей регистрации и логина.
    private fun registerDialog() {
        //? инициализируем alertDialog
        val alertDialog = AlertDialog.Builder(requireContext())
        //? заголовок
        alertDialog.setTitle(R.string.register_alert_dialog_header)
        //? основное содержения
        alertDialog.setMessage(R.string.register_alert_dialog_message)
        alertDialog.setIcon(R.drawable.icon_alert_dialog)
        //? убераем возможность закрывать диалоговое окно нажитием в сторону
        alertDialog.setCancelable(false)
        //? ставим кнопку в алерт сообщение для подтверждения
        //? в случае подтверждения кидаем на регистрацию
        alertDialog.setPositiveButton(R.string.register_alert_dialog_positive_button) { _, _ ->
            val action = FragmentProfileDirections.actionFragmentProfileToFragmentRegister()
            findNavController().navigate(action)
        }
        //? в случае отказа кидаем на фрагмент потерял и говорим что без регистрации доступ в профиль закрыт
        alertDialog.setNegativeButton(R.string.register_alert_dialog_negative_button) { _, _ ->
            val action = FragmentProfileDirections.actionFragmentProfileToFragmentLost()
            findNavController().navigate(action)
            Toast.makeText(requireContext(), R.string.register_alert_dialog_negative_toast, Toast.LENGTH_LONG).show()
        }

        //? создаем и запускаем диалог
        alertDialog.create()
            .show()
    }
}























