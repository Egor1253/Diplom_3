package constants;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomData {

    public static String RANDOM_EMAIL = RandomStringUtils.randomAlphabetic(10) + "@yandex.ru";
    public static String RANDOM_PASS = RandomStringUtils.randomNumeric(6);
    public static String RANDOM_PASS_5_CHAR = RandomStringUtils.randomNumeric(5); //генерирует пароль с 5 цифрами для теста с неправильным паролем
    public static String RANDOM_NAME = RandomStringUtils.randomAlphabetic(10);
}