# Задача "PhoneBook Hibernate Flyway"

***Реализовать задачу «PhoneBook Vue Server» из курса
клиентская веб-разработка с использованием Spring Boot и
Spring MVC для backend’а.***

**Хранить данные в БД:** 
- В качестве СУБД использовать **PostgreSQL**
- Использовать **Hibernate**
- Использовать **Flyway** для миграций

*Также:*
- Настроить логирование в файл и консоль, добавить
  логирование для всех методов сервисов



    Требования по реализации:
- Реализовать задачу с помощью Vue.js
- Сделать вёрстку при помощи Bootstrap
- Библиотеки подключать с помощью npm
- Настроить сборку через Webpack


    Условия задачи "PhoneBook Vue Server":

***Необходимо сделать веб-страницу – телефонную книгу.***

1. На странице должна быть таблица с контактами.

2. Сделать кнопку для удаления контакта.
   По нажатию на нее контакт должен удаляться, при этом номера контактов пересчитываются.
3. Также сделать кнопку для редактирования контакта.

4. Рядом с таблицей нужно сделать форму с полями Фамилия, Имя, Номер телефона и кнопкой для добавления строки.
5. По нажатию на кнопку должна выполняться валидация, что все поля заполнены.
6. Незаполненные поля нужно как-то выделить визуально. И нужно добавить на страницу сообщение, что необходимо заполнить поле «Имя» и т.д., смотря какие поля не заполнены.
   Если валидация прошла успешно, то все поля очищаются, а в таблицу в конец добавляется новая строка с этими данными. При этом должны сброситься стили и сообщения валидации.

***Усложнения по задаче:***
1.	Сделайте, чтобы по нажатию на кнопку удаления строки вылезало сообщение-confirmation. Библиотеку для этого выберите и подключите сами. Строка должна удаляться только если пользователь на это согласился
2.	Валидация, что контакт с таким номером уже добавлен. В этом случае выводить сообщение валидации, а строку в таблицу не добавлять
3.	Сделать слева дополнительный столбец с чекбоксами для выбора строк.
      При нажатии на кнопку удаления, удалять все выбранные строки
4.	В столбце с чекбоксами в шапке сделать еще один чекбокс. Если его выбирают, то выбираются все строки таблицы. Если его убирают, то очищается выбор всех строк
5.	Над таблицей сделать фильтр – текстовое поле, в которое пользователь может вводить текст. И рядом две кнопки - одна применяет фильтр, вторая сбрасывает. Фильтр ищет по всем полям, без учета регистра. Если фильтр сбросят, то должны показаться все данные.


    Настройка "Webpack":

Настройте для проекта PhoneBook Vue Server сборку через Webpack

*Нужно настроить:*
- Однофайловые Vue компоненты
- Babel и переписать код на ES 2015
- SCSS
- Минификацию и создание source-map