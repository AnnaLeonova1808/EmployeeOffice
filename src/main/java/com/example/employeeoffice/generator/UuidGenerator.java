package com.example.employeeoffice.generator;

import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;
import java.util.UUID;

@RequiredArgsConstructor
public class UuidGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException { //генерирует уникальный идентификатор. Для этого он использует текущее время в миллисекундах и вызывает метод UUID.randomUUID(), чтобы создать случайный UUID.
        long currTimeMillis = System.currentTimeMillis();
        UUID uuid = UUID.randomUUID();

        return concatInHexFormat(currTimeMillis, uuid); // вызов метода для объединения времени и первых 16 символов UUID в шестнадцатеричной форме.
    }
    private byte[] concatInHexFormat(long currTimeMillis, UUID uuid) {
        String uuidStr = uuid.toString().replace("-", "");
        String millisHex = Long.toHexString(currTimeMillis);
        String sequenceHex = uuidStr.substring(0, 16);

        String concatenated = millisHex + sequenceHex;
        byte[] result = new byte[16];

        for (int i = 0; i < 16; i++) {
            int index = i * 2;
            String hexByte = concatenated.substring(index, index + 2);
            result[i] = (byte) Integer.parseInt(hexByte, 16);
        }

        return result;
    }

}
//    private char[] concatInHexFormat(long currTimeMillis, UUID uuid) { //принимает текущее время в миллисекундах и UUID
//        String uuidStr = uuid.toString().replace("-", ""); //преобразует UUID в строку и удаляет все дефисы, чтобы получить только символы UUID.
//        String millisHex = Long.toHexString(currTimeMillis); //преобразует текущее время в миллисекундах (currTimeMillis) в его шестнадцатеричное представление. Затем результат сохраняется в переменной millisHex в виде строки.
//        String sequenceHex = uuidStr.substring(0, 16); // берет первые 16 символов из строки uuidStr (которая содержит только символы UUID без дефисов) и сохраняет их в переменной sequenceHex как шестнадцатеричное представление.
//
//        String concatenated = millisHex + sequenceHex; //объединяет первые 16 символов UUID и шестнадцатеричное представление времени в строку и сохраняются в переменной concatenated.
//
//        return concatenated.toCharArray(); //возвращает массив символов, представляющий объединенное шестнадцатеричное представление времени и первых 16 символов UUID.
//    }