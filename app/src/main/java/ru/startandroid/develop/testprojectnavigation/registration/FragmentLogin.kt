package ru.startandroid.develop.testprojectnavigation.registration

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.startandroid.develop.testprojectnavigation.R
import ru.startandroid.develop.testprojectnavigation.databinding.FragmentLoginBinding
import ru.startandroid.develop.testprojectnavigation.utils.hideKeyboard

//? макет фрагмента готов, остается подключить логику.
class FragmentLogin: Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        binding.apply {
            backToRegisterTextView.setOnClickListener {
                activity?.onBackPressed()
            }

            loginButtonLogin.setOnClickListener {
                performLogin(it)
            }
        }
    }

    //? почти тоже самое что и при регистрации, проверяем поля, перекидываем в профиль
    private fun performLogin(view: View) {
        val email = binding.emailEdittextLogin.text.toString()
        val password = binding.passwordEdittextLogin.text.toString()
        val isRegistered = true

        if (email.isNotEmpty() && password.isNotEmpty()) {
            val action = FragmentLoginDirections.actionFragmentLoginToFragmentProfile(isRegistered)
            findNavController().navigate(action)
            hideKeyboard(view)
        } else {
            Toast.makeText(requireContext(), R.string.fragment_register_login_toast_fill_data, Toast.LENGTH_SHORT).show()
        }
    }
}