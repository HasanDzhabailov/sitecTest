<h1 align="center">Test Sitec</h1>


<h2>Tech stack</h2>
<ul>
<li>Kotlin based, Coroutines + LiveData for asynchronous</li>
<li>Dagger for dependency injection</li>
<li>Jetpack: </li>
  <ul>
  <li>Lifecycle</li>
  <li>ViewModel</li>
  <li>Room</li>
  <li>View binding</li>
  </ul>
<li> Architecture: MVVM Architecture </li>
<li> Navigation Component</li>
<li> Retrofit2 </li>
<li> OkHTTP</li>
<li> Gson</li>
</ul>

<h2>Исходные данные</h2>
<p>
На сервере опубликован HTTP сервис в базе 1С </br>
https://dev.sitec24.ru/UKA_TRADE/</br>
Имя сервиса – «MobileClient»</br>
Логин – http</br>
Пароль – http</br>
Каждое мобильное устройство подключается к сервису со своим идентификатором (IMEI), при отсутствии GSM модуля может быть случайный UUID</br>
Сервис имеет несколько методов:</br></br>
Получить список пользователей (GET)</br></br>
Шаблон адреса запроса:</br></br>
/{IMEI}/form/users/</br></br>
Выполнить аутентификацию (GET)</br></br>
Шаблон адреса запроса:</br></br>
/{IMEI}/authentication/</br></br>
Доп.параметры запроса:</br></br>
uid – uid пользователя из списка пользователей</br></br>
pass – введенный пароль</br></br>
copyFromDevice – всегда «false»</br></br>
nfc – всегда «» (пустая строка)
</p>
<h2>Задание</h2>
<p>
Создать приложение для Android, которое будет получать список пользователей, отображать список с выбором пользователя (spinner) и поле ввода пароля.
По кнопке «Вход» приложение должно делать запрос на сервер и при ответе 200  перейти на следующий экран, отображать на новом экране ответ в виде списка(НУЖНО отображать все успешные попытки), а также сохранять этот результат  в базу данных.
</p>
<p>
При не удачных попытках входа в программу – выводить ошибку в виде всплывающего сообщения или диалога.
Использовать все доступные вам практики и паттерны (jetpack compose использовать не нужно)
Пример полной строки запроса:
https://dev.sitec24.ru/UKA_TRADE/hs/MobileClient/111111111111111/form/users?
Для сертификата SSL отключить проверку
</p>
