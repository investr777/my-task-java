====================================================
НАЗВАНИЕ ПРОЕКТА
====================================================
my_app_run
----------------------------------------------------


====================================================
ФУНКЦИОНАЛ
====================================================
1. Регистрация нового пользователя.
2. Создание новой записи о беге пользователя, содержащую в себе: время, дистанцию и дату забега.
3. Получение записи по ID. 
4. Редактирование выбранной записи.
5. Удаление выбранной записи.
6. Вывод полного списка записей бега пользователя.
7. Построение отчётов по неделям на основании записей о беге пользователя, которые содержат в себе: среднюю скорость, среднее время, общую дистанцию, годовой номер недели и диапозон дат этой недели.
----------------------------------------------------


====================================================
СБОРКА ПРОЕКТА
====================================================
Сборку проекта осуществляется командой: gradle build
----------------------------------------------------


====================================================
ЗАПУСК ПРОЕКТА
====================================================
Запуск проекта осуществляется командой: gradle runApp
----------------------------------------------------

====================================================
SWAGGER
====================================================
Swagger доступен по URL: localhost:8080/swagger-ui.html
----------------------------------------------------


====================================================
РАБОТА ПРИЛОЖЕНИЯ
====================================================
По умолчанию создаются два пользователя и несколько записей: 
№1 login: "user1", passowrd: "123".
№2 login: "user2", passowrd: "321".

Регестрации нового пользователя, применяется POST запрос по URL: localhost:8080/registration

Получение всех записей пользователя, применяется GET запрос по URL: localhost:8080/records

Добавление новой записи о беге, POST запрос по URL: localhost:8080/records

Редактирование выбранной записи, PUT запрос по URL: localhost:8080/records/{recordId}

Удаление выбранной записи, DELETE запрос по URL: localhost:8080/records/{recordId}

Постороение отчётов по неделям, GET запрос по URL: localhost:8080/report/by_weeks
--------------------------------------------------------------------------------------------------------
