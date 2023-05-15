# language: ru
@mainpage
#noinspection NonAsciiCharacters
Функция: Тесты главной страницы

  Предыстория:
    Дано пользователь заходит на главную страницу

  @success
  Сценарий: Тест наличия элементов на странице
    Тогда пользователь видит, что элементы присутствуют на странице

  @success
  Сценарий: Тест слайдера
    Когда пользователь нажимает на правую кнопку слайдера
    Тогда слайдер показывает следующий слайд

  @success
  Структура сценария: Тест отображения основного меню при скроллинге на заданное количество пикселей вниз
    Когда пользователь прокручивает вниз <количество> пикселей
    Тогда пользователь все ещё видит основное меню

    Примеры:
      | количество |
      | 1000       |

  @success
  Сценарий: Тест отображения основного меню при скроллинге до заданного элемента
    Когда пользователь прокручивает до заданного элемента
    Тогда пользователь все ещё видит основное меню

  @success
  Сценарий: Тест перехода по меню
    Когда пользователь нажимает на кнопку меню
    И в меню нажимает на кнопку перехода на другую страницу
    Тогда пользователь видит, что перешел на эту страницу