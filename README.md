# Book-service
REST-сервис для управления списком всех книг

# Инструкция по сборке и запуску (Linux).

## Для запуска необходимо:
* Maven
* Docker
* Docker Compose

## Сборка и запуск

```
git clone https://github.com/TheFreaky/book-service.git
cd book-service/
sh ./build.sh
docker-compose up -d
```

## Результатом должно быть:
* Docker Compose собрал Docker images и запустил их
* Есть доступ к http://localhost:8080

# API

Автоматически сгенерированная документация REST API:
http://localhost:8080/swagger-ui.html

# Demo
Демо стенд:

https://book-service-demo.herokuapp.com/swagger-ui.html