# CharFreqREST
## Описание
Это веб-приложение - RESTful-сервис, позволяющий определить частоту вхождения каждого символа в строку.
Разработано на Java 17 + Spring Boot 3.
## Входные данные
Ограничения:
- Строка должна быть не пустой
- Строка должна быть не больше 20 млн символов

Формат входных данных:
```json
{
  "line": "aab"
}
```
## Выходные данные
Формат выходных данных:
```json
{
  "data": {
    "a": 2,
    "b": 1
  }
}
```
## Запуск
### Maven
```shell
./mvnw springboot:run
```
Либо

Linux/MacOS:
```shell
./mvnw package
```
Или Windows:
```shell
./mvnw.cmd package
```
Затем:
```shell
java -jar target/CharFreqREST-0.0.1-SNAPSHOT.jar
```
### Docker
```shell
docker build -t charfreqrest .
docker run -p8080:8080 charfreqrest
```
Порт по умолчанию: 8080


Swagger: http://localhost:8080/swagger-ui/index.html
