- LocalDateTime：不可变的、线程安全的。

```
LocalDateTime today = LocalDateTime.now();
LocalDateTime tomorrow = today.plusDays(1);
System.out.println(tomorrow);
//Date to LocalDataTime
Date date = new Date();
Instant instant = date.toInstant();
ZoneId zoneId = ZoneId.systemDefault();
LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
//LocalDataTime to Date
LocalDateTime localDateTime = LocalDateTime.now();
ZonedDateTime zdt = localDateTime.atZone(zoneId);
Date date = Date.from(zdt.toInstant());
```

