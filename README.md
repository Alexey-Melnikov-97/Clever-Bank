# Clever-Bank

Clever-Bank - консольное приложение для выполнения различных денежных операций.

## Инструкция по установке и запуску проекта
1. **Установка зависимостей.**
   Для успешного запуска проекта необходимо выполнить следующие шаги:
   Установите PostgreSQL версии 15 на вашем компьютере.
   Скопируйте файл базы данных **"dump-clever-202308311349.sql"** из корневой папки проекта.
2. **Установка базы данных.**
   Откройте командную строку или терминал и перейдите в директорию вашего PostgreSQL. Например:
   **cd C:\Program Files\PostgreSQL\15\bin.**
   В командной строке выполните следующую команду, заменив <путь_к_файлу> на реальный путь к файлу базы данных:
   **psql -U postgres -f <путь_к_файлу>\dump-clever-202308311349.sql.**
   Программа запросит вас ввести пароль для пользователя, и база данных будет установлена.
3. **Конфигурация.**
   Откройте класс **ConnectionMaker** и внесите необходимые изменения для установки соединения с вашей базой данных. Вам может потребоваться обновить URL, имя пользователя и пароль. 
   Также создайте в папке **src** две новые папки для сохранения чеков и выписок: **"check"** и **"statement-money"**.
4. **Запуск проекта.**
   Запустите проект из класса **Main**. Убедитесь, что проект успешно запускается и готов к использованию.