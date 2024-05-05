package com.cerebra.mailsender.utility;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Utilities {
    public static LocalDateTime formatTimestamp(double timestamp, String zoneId) {
        Instant instant = Instant.ofEpochSecond((long) timestamp);
        return instant.atZone(ZoneId.of(zoneId)).toLocalDateTime();
    }
}
