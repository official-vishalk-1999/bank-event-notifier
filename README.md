# bank-event-notifier

A banking transaction notification system built with RabbitMQ. When a transaction comes in, it gets published to an exchange and two consumers pick it up independently — one for fraud detection, one for sending notifications. Built this to understand how message queues work in practice.

---

## Stack

- Spring Boot, Java 17
- RabbitMQ (AMQP)

---

## How it works

Transaction event comes in via REST → producer publishes it to a direct exchange with two routing keys → fraud queue and notification queue each get a copy → consumers process them independently and store results in memory.

```
POST /api/event
        ↓
   bank-exchange
    ↙         ↘
fraud-queue   notification-queue
    ↓               ↓
FraudConsumer  NotificationConsumer
```

You can then query the result:
```
GET /api/result/{transactionId}
```

---

## API

```
POST /api/event     → send transaction event, returns transaction ID
GET  /api/result/{id} → get fraud check + notification result for that transaction
```

---

## Run locally

Need RabbitMQ running locally (default port 5672).

```bash
git clone https://github.com/official-vishalk-1999/bank-event-notifier
cd bank-event-notifier
./mvnw spring-boot:run
```

Open http://localhost:8080

---

## Notes

- Two consumers run independently — if one fails the other still processes
- Results stored in ConcurrentHashMap (in-memory) — resets on restart
- RabbitMQ config (exchange, queues, bindings) all defined in RabbitMQConfig.java
