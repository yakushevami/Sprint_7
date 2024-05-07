# Задание.

## Документация API: [Сервис Яндекс Самокат](https://qa-scooter.praktikum-services.ru/docs/ "API сервиса Яндекс Самокат")

### Протестируй ручки.

Проверь, что они корректно работают и выдают нужные ошибки.
1. Создание курьера
   - курьера можно создать;
   - нельзя создать двух одинаковых курьеров;
   - чтобы создать курьера, нужно передать в ручку все обязательные поля;
   - запрос возвращает правильный код ответа;
   - успешный запрос возвращает ok: true;
   - если одного из полей нет, запрос возвращает ошибку;
   - если создать пользователя с логином, который уже есть, возвращается ошибка.
2. Логин курьера
   - курьер может авторизоваться;
   - для авторизации нужно передать все обязательные поля;
   - система вернёт ошибку, если неправильно указать логин или пароль;
   - если какого-то поля нет, запрос возвращает ошибку;
   - если авторизоваться под несуществующим пользователем, запрос возвращает ошибку;
   - успешный запрос возвращает id.
3. Создание заказа
   - можно указать один из цветов — BLACK или GREY;
   - можно указать оба цвета;
   - можно совсем не указывать цвет;
   - тело ответа содержит track.
Чтобы протестировать создание заказа, нужно использовать параметризацию.
4. Список заказов.
Проверь, что в тело ответа возвращается список заказов.

5. Отчёт Allure. 
Сгенерируй его и запушь в репозиторий.
   - если передать неверный номер заказа, запрос вернёт ошибку.
3. Получить заказ по его номеру
   - успешный запрос возвращает объект с заказом;
   - запрос без номера заказа возвращает ошибку;
   - запрос с несуществующим заказом возвращает ошибку.
