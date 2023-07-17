# language: ru

@api
Функционал: Тестирование API погоды

  @positive
  Структура сценария: Проверка работы запроса текущей погоды для нескольких городов
    Дано Имеется ключ API к WeatherStack
    Когда Отправляется запрос текущей погоды для города "<Город>"
    То Ответ должен содержать корректные значения для: "<Страна>", "<Широта>", "<Долгота>", "<Часовой пояс>", "<Смещение по UTC>"

    Примеры: Проверка корректности значений
      | Город     | Страна                   | Широта | Долгота | Часовой пояс     | Смещение по UTC |
      | New York  | United States of America | 40.714 | -74.006 | America/New_York | -4.0            |
      | Moscow    | Russia                   | 55.752 | 37.616  | Europe/Moscow    | 3.0             |
      | Paris     | France                   | 48.867 | 2.333   | Europe/Paris     | 2.0             |
      | Berlin    | Germany                  | 52.517 | 13.400  | Europe/Berlin    | 2.0             |
      | Minsk     | Belarus                  | 53.900 | 27.567  | Europe/Minsk     | 3.0             |

  @negative
  Структура сценария: Проверка ошибок из списка API Errors
    Дано Имеется ключ API к WeatherStack
    Когда Отправляется неверный запрос "<Неверный запрос>"
    То Ответ должен содержать "<Сообщение об ошибке>"

    Примеры: Проверка сообщений об ошибке
      | Неверный запрос | Сообщение об ошибке                                                     |
      | forecast        | Your current subscription plan does not support weather forecast data   |
      | historical      | Your current subscription plan does not support historical weather data |
      | autocomplete    | Your current Subscription Plan does not support this API Function       |
      | nonexistent     | This API Function does not exist                                        |