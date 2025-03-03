# 🛠️ Backend: Основы проектирования моделей и валидации

## ✅ **1. Структура базы данных**
Перед созданием бэкенд-проекта **нужно учитывать структуру БД**. Важно продумать **какие модели (сущности) будут** и **как они будут связаны между собой**.

### **🔒 Основные шаги при проектировании моделей:**
1. **Определить основные сущности (модели).**
2. **Определить связи между ними** (`OneToOne`, `OneToMany`, `ManyToOne`, `ManyToMany`).
3. **Добавить аннотации JPA** (`@Entity`, `@Table`, `@Column(nullable = false)`, `@Id` и др.).
4. **Добавить валидацию** (`@NotNull`, `@Size`, `@Pattern`, `@Email`, `@Min/@Max`).
5. **Продумать поведение данных** (`@PrePersist`, `@PreUpdate`).

---

## 📃 **2. JPA-аннотации для работы с БД**
### 👉 **Обязательные аннотации для сущностей:**
| Аннотация | Описание |
|-----------|----------|
| `@Entity` | Определяет, что класс является **сущностью в БД**. |
| `@Table(name = "название")` | Определяет имя таблицы в базе данных. |
| `@Id` | Указывает **первичный ключ** таблицы. |
| `@GeneratedValue(strategy = GenerationType.IDENTITY)` | Автоматически генерирует `ID`. |
| `@Column(nullable = false)` | Поле **обязательно** для заполнения. |
| `@Column(unique = true)` | Поле **уникальное** (например, email, username). |
| `@Enumerated(EnumType.STRING)` | Хранит `enum` в виде **строки**. |
| `@PrePersist` | Вызывается **до сохранения** новой записи в БД. |
| `@PreUpdate` | Вызывается **перед обновлением** записи в БД. |

---

## 🔗 **3. JPA-аннотации для связей между таблицами**
| Аннотация | Описание |
|-----------|----------|
| `@OneToOne` | Один к одному (**User** → **Profile**). |
| `@OneToMany` | Один ко многим (**User** → **Certificates**). |
| `@ManyToOne` | Многие к одному (**VolunteerHours** → **User**). |
| `@ManyToMany` | Многие ко многим (**User** ↔ **Event**). |
| `@JoinColumn(name = "column_name")` | Указывает **внешний ключ** для связи. |

### 👉 **Пример связи `User` и `VolunteerHours`**
```java
@Entity
@Table(name = "volunteer_hours")
public class VolunteerHours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Связь с таблицей users
}
```

---

## 📂 **4. Валидация данных в Spring Boot**
| Аннотация | Описание |
|-----------|----------|
| `@NotNull` | Поле не может быть `null`. |
| `@NotEmpty` | Поле **не может быть пустым** (`""`). |
| `@Size(min = 2, max = 255)` | Ограничивает **длину строки**. |
| `@Pattern(regexp = "regex")` | Проверяет строку по **регулярному выражению**. |
| `@Email` | Проверяет **корректность email**. |
| `@Min(значение)` / `@Max(значение)` | Ограничение **числового диапазона**. |
| `@Past` / `@Future` | Дата **в прошлом** / **в будущем**. |

### 👉 **Пример валидации в `User`**
```java
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "Name cannot be empty")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @Column(nullable = false, unique = true)
    @Email(message = "Email must be valid")
    private String email;
}
```

---

## 📝 **5. Итог: что учитывать при проектировании моделей?**
✅ **Какие сущности (модели) будут в системе?**

✅ **Как они будут связаны между собой?**

✅ **Какие ограничения и валидацию использовать?**

✅ **Как автоматизировать поведение сущностей (`@PrePersist`, `@PreUpdate`)?**

