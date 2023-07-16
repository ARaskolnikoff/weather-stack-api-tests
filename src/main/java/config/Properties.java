package config;

import org.aeonbits.owner.ConfigFactory;

/**
 * Класс управления глобальными настройками
 *
 * @author Петр Белинский
 */
public class Properties {

    /**
     * Экземпляр интерфейса TestProperties для доступа к конфигурационным свойствам.
     */
    public static TestProperties testProperties = ConfigFactory.create(TestProperties.class);
}