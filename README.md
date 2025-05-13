# 🚀 Origin Energy URL Shortener REST API

A Spring Boot REST API for shortening URLs and retrieving information about the original long URLs.

---

## 📦 Features

- Shorten long URLs with unique keys
- Retrieve original long URL using short key
- View metadata (e.g. creation time)
- Built with Spring Boot + Gradle

---

## 🚀 Getting Started
Ensure you have at Java version 17 + installed on your machine

### 📁 Clone the repo

```bash
git https://github.com/Nathan-Cabrita/origin-energy-url-shortener.git
cd origin-energy-url-shortener
```


### 🔥 Run the application
#### Gradle
```bash
./gradlew bootRun
```

#### Docker
```bash
docker build -t origin-energy-url-shortener .
docker run -p 8080:8080 origin-energy-url-shortener
```


### Make Requests
This endpoint is used to receive a shortened URL which can be used to get redirected to your original URL

``` http://localhost:8080/short-url?url={YOUR_URL_HERE} ```

This endpoint is used to get redirected to your original long URL

``` http://localhost:8080/{YOUR_SHORT_URL_KEY_HERE}```

This endpoint is used to receive information about a long URL from a shortURL key

``` http://localhost:8080/{YOUR_SHORT_URL_KEY_HERE}/info```
