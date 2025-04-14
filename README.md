# 📚 Digital Library

**Digital Library** — bu Java va Spring Boot texnologiyalari asosida ishlab chiqilgan raqamli kutubxona loyihasi bo‘lib, foydalanuvchilar ro‘yxatdan o‘tishlari, tizimga kirishlari, kitoblarni ko‘rishlari, ijaraga olishlari va qaytarishlari mumkin. Loyihada foydalanuvchilar va adminlar uchun turli rollar mavjud.

## 🚀 Qanday ishga tushiriladi

### 1. Repozitoriyani klonlash

```bash
git clone https://github.com/your-username/digital-library.git
cd digital-library
```

### 2. Loyihani yig‘ish

```bash
./mvnw clean install
```

### 3. Dasturni ishga tushirish

```bash
./mvnw spring-boot:run
```

Dastur odatda quyidagi manzilda ishga tushadi:

```
http://localhost:8080
```

### 4. Swagger UI orqali API hujjatlari

```
http://localhost:8080/swagger-ui/index.html
```

## 🔐 Autentifikatsiya qanday ishlaydi?

Loyihada JWT (JSON Web Token) asosidagi autentifikatsiya ishlatilgan. Foydalanuvchi tizimga kirgach, server unga JWT token yuboradi. Keyingi barcha so‘rovlar shu token bilan amalga oshiriladi.

### Foydalanuvchi rollari:
- `ROLE_USER` — oddiy foydalanuvchilar
- `ROLE_ADMIN` — adminlar (kitoblarni boshqaradi)

Token HTTP so‘rovlarining `Authorization` header qismida quyidagicha yuboriladi:

```
Authorization: Bearer <JWT_TOKEN>
```

## 🔌 API Namunalari

### 🔸 Ro‘yxatdan o‘tish (Register)

**URL:** `POST /api/auth/register`

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username": "user1", "password": "pass123"}'
```

### 🔸 Tizimga kirish (Login)

**URL:** `POST /api/auth/login`

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username": "user1", "password": "pass123"}'
```

**Natija:** JWT token quyidagi ko‘rinishda qaytariladi:
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6..."
}
```

### 🔸 Kitoblar ro‘yxatini olish

**URL:** `GET /api/book`

```bash
curl -X GET http://localhost:8080/api/book \
  -H "Authorization: Bearer <JWT_TOKEN>"
```

### 🔸 Kitob qo‘shish (faqat adminlar)

**URL:** `POST /api/book`

```bash
curl -X POST http://localhost:8080/api/book \
  -H "Authorization: Bearer <JWT_TOKEN>" \
  -H "Content-Type: application/json" \
  -d '{"title": "Clean Code", "author": "Robert C. Martin"}'
```

### 🔸 Kitobni ijaraga olish

**URL:** `POST /api/rental/rent?bookId=1`

```bash
curl -X POST "http://localhost:8080/api/rental/rent?bookId=1" \
  -H "Authorization: Bearer <JWT_TOKEN>"
```

### 🔸 Ijaradagi kitobni qaytarish

**URL:** `POST /api/rental/return?bookId=1`

```bash
curl -X POST "http://localhost:8080/api/rental/return?bookId=1" \
  -H "Authorization: Bearer <JWT_TOKEN>"
```

## 🧪 Test qilish

Postman, Swagger yoki `curl` orqali yuqoridagi so‘rovlar yordamida test qilishingiz mumkin. Swagger interfeysi ayniqsa qulay:

➡ `http://localhost:8080/swagger-ui/index.html`

## 📜 Litsenziya

Ushbu loyiha ochiq manba (open source) va foydalanish uchun bepul.


