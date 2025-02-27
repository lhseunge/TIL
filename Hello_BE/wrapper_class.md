# Question

- 래퍼 클래스가 무엇인지, 어떻게 사용해야되는지 알려주세요.

---

# Answer

- 래퍼 클래스(`Wrapper Class`)는 기본 타입(`Primitive type`)을 객체로 다루기 위한 클래스를 나타냅니다.

| 기본 타입(`Primitive type`) | 래퍼 클래스(`Wrapper Class`) |
| --- | --- |
| byte | Byte |
| char | Character |
| int | Integer |
| float | Float |
| double | Double |
| boolean | Boolean |
| long | Long |
| short | Short |

### 래퍼 클래스를 사용하는 이유

1. 기본 타입을 `객체` 로 변환할 수 있습니다.
2. `util` 관련 클래스나 `Collection` 의 경우 기본 타입은 사용할 수 없습니다. 

### 기본 타입과 래퍼 클래스의 전환

- 박싱(boxing)과 언박싱(unboxing)을 통해 전환이 가능합니다.

- boxing : 기본 타입 → 래퍼 클래스
    
    ```java
    // boxing
    Integer num = new Integer(44);
    
    // auto boxing
    List<Integer> l = List.of(1,3); 
    ```
    

- unboxing : 래퍼 클래스 → 기본 타입

```java
// unboxing
int n = num.intValue();

// auto unboxing
Integer num = 4;
int n = num;
```

## Reference

> 
> 

[래퍼 클래스란(Wrapper Class)?](https://medium.com/@s23051/%EB%9E%98%ED%8D%BC-%ED%81%B4%EB%9E%98%EC%8A%A4%EB%9E%80-wrapper-class-cc5aa6f7cdd1#id_token=eyJhbGciOiJSUzI1NiIsImtpZCI6Ijc2M2Y3YzRjZDI2YTFlYjJiMWIzOWE4OGY0NDM0ZDFmNGQ5YTM2OGIiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiIyMTYyOTYwMzU4MzQtazFrNnFlMDYwczJ0cDJhMmphbTRsamRjbXMwMHN0dGcuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiIyMTYyOTYwMzU4MzQtazFrNnFlMDYwczJ0cDJhMmphbTRsamRjbXMwMHN0dGcuYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMDQyNTc0NzU3MjQ3ODM0NDE5NDUiLCJlbWFpbCI6Imxoc2V1bmdlQGdtYWlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJuYmYiOjE3NDA2NDgzODIsIm5hbWUiOiLsnbTtmITsirkiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDMuZ29vZ2xldXNlcmNvbnRlbnQuY29tL2EvQUNnOG9jSm44UWhmNjhiNG9VQ19QOGZmcG5BUG9iVG4wZjYtLWRqUV9OOVNjbmJoUFk5OE5oVT1zOTYtYyIsImdpdmVuX25hbWUiOiLtmITsirkiLCJmYW1pbHlfbmFtZSI6IuydtCIsImlhdCI6MTc0MDY0ODY4MiwiZXhwIjoxNzQwNjUyMjgyLCJqdGkiOiJmNWRjM2U4OWYyMmJiYmNhMDZmMDFkYTNiM2RjYjA5YTgzOGZlNWVlIn0.OmU6dwXPJwPQWdJq9F8Y63HMT3rBFjdQ36wO5y7qn5CuLa2GpbLItbYvYCuKcYPKPct3fvu-H7QirJeUx4-_dvjenOiRVIfNOzESG_Qac57-OWeuI4ZmNUKIdd2IAYynWjcWwwHbhrNB5hA0OmZIPFqzSjOLuTBS6SwSVBF9fcmxRcuARsotFY1ctuedgWbbjbMY8DScccyI_1San5tEqifgcaqIBGsV1lSmNM_0QF31waqoWWVLdUMEY0McKJXyChM9xdMryfy-MwkQ_kZQnZpF0N2Z61OONxYNw9SyymBjhG53ZzkSJoFY6nd9Xya7KIqxP2sg-P0tdKX_ujKOJw)
