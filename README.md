## Фреймворк _«WeatherStackApiTests»_

### Описание архитектуры фреймворка

- `Проверка API WeatherStack` (файл):
    - Описывает функциональность API погоды и содержит два раздела: `@positive` и `@negative`
    - Каждый раздел содержит структуру сценария и примеры для проверки корректности значений
      и сообщений об ошибках соответственно


- `CucumberRunnerTest` (класс):
    - Представляет запускающий класс для выполнения тестовых сценариев
    - Использует аннотации `JUnit 5` для настройки и запуска тестов
    - Использует свойства из файла `junit-platform.properties`, который
      является файлом конфигурации для `JUnit Platform`


- `WeatherStackApiStepDefinitions` (класс):
    - Содержит реализацию шагов, описанных в файле `Проверка API WeatherStack.feature`
    - Каждый шаг связан с конкретной аннотацией (`@Дано`, `@Когда`, `@То`) и содержит код, выполняющий запросы
      к `API` погоды с использованием `REST Assured` и проверяющий ожидаемые значения и сообщения об ошибках


- `TestProperties` (интерфейс):
    - Представляет конфигурационные свойства, необходимые для выполнения тестов
    - Использует аннотации из библиотеки `Aeonbits Owner Config` для загрузки значений 
      свойств из файла `test.properties`, который содержит `URL` и `ключ API` для сервиса `WeatherStack`


- `Properties` (класс):
    - Предоставляет статическую переменную `testProperties`, которая представляет экземпляр интерфейса `TestProperties`
    - Используется для предоставления доступа к конфигурационным свойствам в других частях кода

### Структура проекта
````
src
├── main
│   └── java
│       └── config
│           ├── Properties.java
│           └── TestProperties.java              
└── test
    ├── java
    │   └── com
    │       └── weatherstack
    │           ├── defenitions
    │           │   └── BankingTests.java
    │           └── runner
    │               └── CucumberRunnerTest.java
    └── resources
        ├── features
        │   └── Проверка API WeatherStack.feature
        ├── junit-platform.properties
        └── test.properties
````

> `Java 11` | `Cucumber 7` | `JUnit 5` | `Rest Assured` | `Allure`

## Запуск unit-тестов через maven-команды

- ### Запуск позитивного теста

````
mvn test -Dgroups=positive
````

- ### Запуск негативного теста

````
mvn test -Dgroups=negative
````

- ### Просмотр allure-отчета

````
mvn allure:serve
````

Примечание:

>_Allure-отчеты формируются при запуске тестов maven-командой или при запуске тестов из класса CucumberRunnerTest_
