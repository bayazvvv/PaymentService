# Payment Service

Простое Spring Boot приложение для управления платежами с PostgreSQL, Flyway и Swagger/OpenAPI.

## Стек

- Java 17
- Spring Boot 3.3.5
- Spring Web
- Spring Data JPA
- PostgreSQL
- Flyway
- springdoc OpenAPI / Swagger UI

## Структура проекта

```text
src/main/java/bayazvvv/paymentservice
├── config
├── controller
├── dto
├── exception
├── model
├── repository
└── service

src/main/resources
├── application.properties
└── db/migration
```

## Настройки БД

Приложение настроено на вашу PostgreSQL БД:

- URL: `jdbc:postgresql://localhost:5432/payment_service`
- DB schema: `public`
- Username: `postgres`
- Password: `postgres`

Flyway автоматически создаёт таблицу `payments` в схеме `public`.

## Запуск

### 1. Запуск приложения

```bash
./mvnw spring-boot:run
```

Для Windows PowerShell:

```powershell
.\mvnw.cmd spring-boot:run
```

### 2. Swagger UI

- Swagger UI: `http://localhost:8080/swagger-ui.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## REST API

### Создать платеж

`POST /payments`

Request:

```json
{
  "amount": 1000,
  "currency": "KZT",
  "description": "Оплата заказа №123",
  "clientId": 12345
}
```

Response `201 Created`:

```json
{
  "paymentId": 1,
  "status": "PENDING"
}
```

### Получить платеж

`GET /payments/{paymentId}`

Response `200 OK`:

```json
{
  "paymentId": 1,
  "amount": 1000,
  "currency": "KZT",
  "description": "Оплата заказа №123",
  "clientId": 12345,
  "status": "PENDING"
}
```

### Подтвердить платеж

`POST /payments/{paymentId}/confirm`

Response:

```json
{
  "paymentId": 1,
  "status": "CONFIRMED"
}
```

### Отменить платеж

`POST /payments/{paymentId}/cancel`

Response:

```json
{
  "paymentId": 1,
  "status": "CANCELED"
}
```

### Получить все платежи клиента

`GET /clients/{clientId}/payments`

Response:

```json
[
  {
    "paymentId": 1,
    "amount": 1000,
    "currency": "KZT",
    "status": "CONFIRMED"
  },
  {
    "paymentId": 2,
    "amount": 500,
    "currency": "KZT",
    "status": "PENDING"
  }
]
```

## Примеры curl

```bash
curl -X POST http://localhost:8080/payments \
  -H "Content-Type: application/json" \
  -d '{"amount":1000,"currency":"KZT","description":"Оплата заказа №123","clientId":12345}'
```

```bash
curl http://localhost:8080/payments/1
```

```bash
curl -X POST http://localhost:8080/payments/1/confirm
```

```bash
curl -X POST http://localhost:8080/payments/1/cancel
```

```bash
curl http://localhost:8080/clients/12345/payments
```

## Ошибки

Пример `404 Not Found`:

```json
{
  "timestamp": "2026-04-03T10:15:30Z",
  "status": 404,
  "error": "Not Found",
  "message": "Payment with id 999 was not found",
  "path": "/payments/999"
}
```

Пример `400 Bad Request`:

```json
{
  "timestamp": "2026-04-03T10:15:30Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Payment with id 1 cannot be changed from status CONFIRMED",
  "path": "/payments/1/cancel"
}
```

## Postman

Файл коллекции лежит в репозитории:

- `postman/PaymentService.postman_collection.json`
