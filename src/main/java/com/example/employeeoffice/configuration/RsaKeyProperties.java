package com.example.employeeoffice.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Component
@ConfigurationProperties(prefix = "rsa")
public class RsaKeyProperties {
    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

    public RSAPublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(RSAPublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public RSAPrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(RSAPrivateKey privateKey) {
        this.privateKey = privateKey;
    }
    /**
     * RSAPublicKey publicKey: Поле для хранения открытого ключа RSA.
     * Открытый ключ используется для шифрования данных или верификации подписей,
     * созданных с использованием соответствующего закрытого ключа.
     * В контексте JWT, открытый ключ часто используется серверами или
     * клиентами для проверки подлинности подписанных токенов.
     *
     * RSAPrivateKey privateKey: Поле для хранения закрытого ключа RSA.
     * Закрытый ключ используется для дешифрования данных, зашифрованных с использованием
     * соответствующего открытого ключа, или создания подписей. В контексте безопасности приложений,
     * закрытый ключ часто используется для подписи JWT, обеспечивая тем самым,
     * что подпись может быть проверена любой стороной, имеющей открытый ключ.
     *
     * Этот класс можно использовать в Spring Boot приложении
     * для загрузки и использования пары ключей RSA из конфигурационных файлов.
     * Это особенно полезно, когда приложение нуждается в обработке шифрования или
     * подписи данных, например, при работе с JWT для аутентификации и авторизации пользователей.
     */
}
