# **CRUD Base Library / CRUD 라이브러리**

A lightweight Spring Boot library for simplifying CRUD operations with minimal setup.  
기본 설정만으로 CRUD 작업을 간소화하는 경량 Spring Boot 라이브러리입니다.

## **Features / 기능**

- Generic `BaseService` and `BaseController` to streamline CRUD operations.  
  CRUD 작업을 간소화하는 제네릭 `BaseService`와 `BaseController`.
- Extendable and customizable for various use cases.  
  다양한 사용 사례에 맞게 확장 가능하고 커스터마이징할 수 있습니다.
- Integration-ready for any Spring Boot project.  
  모든 Spring Boot 프로젝트에서 쉽게 통합 가능합니다.

---

## **Installation / 설치**

### **Gradle**

Add the following dependency to your `build.gradle`:  
`build.gradle` 파일에 아래 의존성을 추가하세요.

```groovy
repositories {
    maven {
        url = uri("https://maven.pkg.github.com/sky7214sky72/cws-crud")
        credentials {
            username = project.findProperty("gpr.user") ?: System.getenv("GITHUB_ACTOR")
            password = project.findProperty("gpr.key") ?: System.getenv("GITHUB_TOKEN")
        }
    }
}

dependencies {
    implementation 'com.example:crud-base-library:1.0.0'
}
```

Quick Start / 빠른 시작

1. Create an Entity / 엔티티 생성

Define a JPA entity in your project:
프로젝트에서 JPA 엔티티를 정의하세요.

```java

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String email;

  // Getters and setters
}
```

2. Create a Repository / 레포지토리 생성

Extend the BaseRepository:
BaseRepository를 확장하세요.

```java

@Repository
public interface UserRepository extends BaseRepository<User, Long> {

}
```

3. Extend BaseService / BaseService 확장

```java

@Service
public class UserService extends BaseService<User, Long> {

  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  protected BaseRepository<User, Long> getRepository() {
    return userRepository;
  }
}
```

4. Extend BaseController / BaseController 확장

```java

@RestController
@RequestMapping("/users")
public class UserController extends BaseController<User, Long> {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @Override
  protected BaseService<User, Long> getService() {
    return userService;
  }
}
```

Available Endpoints / 제공되는 엔드포인트

| Method / 메서드 | Endpoint / 엔드포인트 | Description / 설명               |
|--------------|------------------|--------------------------------|
| POST         | `/users`         | Create a new user / 새 사용자 생성   |
| GET          | `/users/{id}`    | Retrieve by ID / ID로 조회        |
| GET          | `/users`         | Retrieve all users / 모든 사용자 조회 |
| PUT          | `/users/{id}`    | Update by ID / ID로 업데이트        |
| DELETE       | `/users/{id}`    | Delete by ID / ID로 삭제          |

License / 라이선스

This project is licensed under the MIT License.
이 프로젝트는 MIT 라이선스 하에 배포됩니다.

---