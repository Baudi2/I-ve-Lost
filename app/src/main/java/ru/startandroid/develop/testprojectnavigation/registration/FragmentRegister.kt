package ru.startandroid.develop.testprojectnavigation.registration

import android.app.Activity
import android.content.Intent
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.startandroid.develop.testprojectnavigation.R
import ru.startandroid.develop.testprojectnavigation.databinding.FragmentRegisterBinding
import ru.startandroid.develop.testprojectnavigation.explain_activtyForResultPhoto
import ru.startandroid.develop.testprojectnavigation.utils.hideKeyboard
import java.lang.Exception

//? макет фрагмента готов, остается подключить логику.
class FragmentRegister: Fragment(R.layout.fragment_register) {

    private lateinit var binding: FragmentRegisterBinding
    private var selectedPhotoUri: Uri? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)

        binding.apply {
            alreadyHaveAccountTextView.setOnClickListener {
                val action = FragmentRegisterDirections.actionFragmentRegisterToFragmentLogin()
                findNavController().navigate(action)
            }

            registerButtonRegister.setOnClickListener {
                performRegister(it)
            }

            selectphotoButtonRegister.setOnClickListener {
                forPhotoButton()
            }
        }
    }

    //? метод для проведения регистрации
    private fun performRegister(view: View) {
        //? получаем данные из edit text которые ввел юзер
        val userName = binding.usernameEditTextRegister.text.toString()
        val email = binding.emailEditTextRegister.text.toString()
        val password = binding.passwordEditTextRegister.text.toString()

        if (userName.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
            val isRegistered = true
            val action = FragmentRegisterDirections.actionFragmentRegisterToFragmentProfile(isRegistered)
            findNavController().navigate(action)
            //? прячем клавиатуру по завершению
            hideKeyboard(view, requireActivity())
        } else {
            //? если поля пустым предупреждаем юзера что их надо заполнить
            Toast.makeText(requireContext(), R.string.fragment_register_login_toast_fill_data, Toast.LENGTH_SHORT).show()
        }
    }

    //? метод через который мы будем получать фото для профиля юзера
    private fun forPhotoButton() {
        //? через этот интент мы переходим в выбор фото
        val intent = Intent(Intent.ACTION_PICK)
        //? ты мы указываем то что тип данных которые мы хотим получить это фото
        intent.type = "image/*"
        //? теперь нам нужен результат выбора юзером фото
        startActivityForResult(intent, 0)
    }

    //? этот метод вызывается всякий раз, когда мы завершаем наше intent из forPhotoButton ()
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //? убеждаемся, что мы используем правильный код запроса, что получение результата из
    //? intent прошло успешно и что полученные нами данные не равны null
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data != null) {
            //? продолжаем и проверяем, какое было выбранное изображение

            explain_activtyForResultPhoto() //!.
            selectedPhotoUri = data.data

            try {
                selectedPhotoUri?.let {
                    if (Build.VERSION.SDK_INT < 28) {
                        val bitmap = MediaStore.Images.Media.getBitmap(
                            activity?.contentResolver,
                            selectedPhotoUri
                        )
                        binding.selectphotoImageviewRegister.setImageBitmap(bitmap)
                        binding.selectphotoButtonRegister.alpha = 0f
                    } else {
                        val source = ImageDecoder.createSource(
                            requireActivity().contentResolver,
                            selectedPhotoUri!!
                        )
                        val bitmap = ImageDecoder.decodeBitmap(source)
                        binding.selectphotoImageviewRegister.setImageBitmap(bitmap)
                        binding.selectphotoButtonRegister.alpha = 0f
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}














