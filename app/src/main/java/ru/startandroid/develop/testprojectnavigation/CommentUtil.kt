package ru.startandroid.develop.testprojectnavigation

/**  appBarConfiguration нужен чтобы отметить высокоуровневые фрагменты т.е. те фрагменты после перехода на которые
     не будет на верху показываться backButton. appBarConfiguration нужно передать в setupActionBarWithNavController
     чтобы он заробатал.*/
fun explain_AppBar() {}


/** подключаем верхний тулбар так чтобы он показывался во всех фрагментах.То что мы настраиваем тут имеет эффект на все
    фрагменты поэтому мы объявили bottomNavigation напрямую в фрагментах т.к. он нам не нужен во всех фрагментах*/
fun explain_setSupportActionBar() {}


/** подключение binding для активити немного отличается от процесса подключение binding к фрагменту
    но код всегда одинаковый так что можно просто записать где-нибудь. */
fun explain_binding() {}