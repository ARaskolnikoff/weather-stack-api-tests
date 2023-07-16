package config;

import org.aeonbits.owner.Config;

import static org.aeonbits.owner.Config.*;

/**
 * Интерфейс, представляющий конфигурационные свойства для выполнения тестов.
 * Он определяет ключи и методы для получения URL и ключа API для сервиса WeatherStack.
 *
 * @author Петр Белинский
 */
@LoadPolicy(LoadType.MERGE)
@Sources({"file:src/test/resources/test.properties"})
public interface TestProperties extends Config{

    /**
     * Возвращает URL для API сервиса WeatherStack.
     *
     * @return URL для API сервиса WeatherStack
     */
    @Key("weatherStackApiUrl")
    String weatherStackApiUrl();

    /**
     * Возвращает ключ API для сервиса WeatherStack.
     *
     * @return ключ API для сервиса WeatherStack
     */
    @Key("weatherStackApiKey")
    String weatherStackApiKey();
}