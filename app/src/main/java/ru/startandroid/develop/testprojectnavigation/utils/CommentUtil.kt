package ru.startandroid.develop.testprojectnavigation.utils

//! Google cloud with access to api key for google maps for our project
//? https://console.cloud.google.com/apis/credentials?highlightKey=2da91166-3831-4f9a-b369-fd0c15950d98&project=electric-totem-310006

/**  appBarConfiguration нужен чтобы отметить высокоуровневые фрагменты т.е. те фрагменты после перехода на которые
     не будет на верху показываться backButton. appBarConfiguration нужно передать в setupActionBarWithNavController
     чтобы он заробатал.*/
fun explainAppBar() {}


/** подключаем верхний тулбар так чтобы он показывался во всех фрагментах.То что мы настраиваем тут имеет эффект на все
    фрагменты поэтому мы объявили bottomNavigation напрямую в фрагментах т.к. он нам не нужен во всех фрагментах*/
fun explainSetSupportActionBar() {}


/** подключение binding для активити немного отличается от процесса подключение binding к фрагменту
    но код всегда одинаковый так что можно просто записать где-нибудь. */
fun explainBinding() {}

/** Теперь нам нужно выяснить, что на самом деле фотография внутри нашего приложения.
 * через полученный объект (data) мы можем получить доступ к Uri выбранной фотографии.
 * Uri представляет собой местоположение выбранной фотографии внутри устройства.
 * И используя этот URI, мы можем получить доступ к изображению в виде bitmap изображения.
 * Мы должны использовать два разных способа получения изображений через bitmap изображение,
 * потому что getBitmap не устарел в версии 28 SDK. Поэтому, мы используем два метода для
 * получения bitmap один для версий ниже 28 и другой для версий выше 28.*/
fun explainActivityForResultPhoto() {}