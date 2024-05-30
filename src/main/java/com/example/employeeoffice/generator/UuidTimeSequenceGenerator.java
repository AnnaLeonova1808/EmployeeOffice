package com.example.employeeoffice.generator;

import lombok.RequiredArgsConstructor;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.Serializable;
import java.util.UUID;
/**
 * Custom UUID generator that combines the current time in milliseconds with a randomly generated UUID.
 */
@RequiredArgsConstructor
public class UuidTimeSequenceGenerator implements IdentifierGenerator {

    /**
     * Generates a custom UUID by combining the current time in milliseconds and a randomly generated UUID.
     *
     * @param session the session
     * @param object the object
     * @return a custom UUID
     * @throws HibernateException if there is an error generating the ID
     */
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        long currTimeMillis = System.currentTimeMillis();

        return concatUUIDAndTime(currTimeMillis, UUID.randomUUID());
    }
    /**
     * Concatenates the current time in milliseconds and a UUID to generate a custom UUID.
     *
     * @param currTimeMillis the current time in milliseconds
     * @param uuid the randomly generated UUID
     * @return a custom UUID
     */
    private UUID concatUUIDAndTime(long currTimeMillis, UUID uuid) {
        String millisHex = Long.toHexString(currTimeMillis);

        String uuidStr = uuid.toString().replace("-", "").substring(0, 16);

        String concatenated = String.format("%016x%s", Long.parseLong(millisHex, 16), uuidStr);
        String concatenatedWithDashes = concatenated.substring(0, 8) + "-" +
                concatenated.substring(8, 12) + "-" +
                concatenated.substring(12, 16) + "-" +
                concatenated.substring(16, 20) + "-" +
                concatenated.substring(20);
        return UUID.fromString(concatenatedWithDashes);
    }
}