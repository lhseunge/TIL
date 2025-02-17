# Question

- 다른 Wrapper 클래스와 달리 String 을 사용할 때는 import 문이 없습니다.
그 이유는?

---

# Answer

- `String.class` 는 `java.lang` 패키지에 포함되어 있어요.
- `java.lang` 패키지는 `import` 구문 없이 자동으로 포함됩니다.
- → `String.class` 뿐만 아니라 `java.lang` 에 포함된 모든 클래스는 `import` 구문 없이 사용할 수 있습니다.
- `java.lang` 에 소속된 하위 클래스
    - [java.lang Package Docs ( java 8 )](https://docs.oracle.com/javase/8/docs/api/java/lang/package-frame.html)
        - [Boolean](https://docs.oracle.com/javase/7/docs/api/java/lang/Boolean.html)
        - [Byte](https://docs.oracle.com/javase/7/docs/api/java/lang/Byte.html)
        - [Character](https://docs.oracle.com/javase/7/docs/api/java/lang/Character.html)
        - [Character.Subset](https://docs.oracle.com/javase/7/docs/api/java/lang/Character.Subset.html)
        - [Character.UnicodeBlock](https://docs.oracle.com/javase/7/docs/api/java/lang/Character.UnicodeBlock.html)
        - [Class](https://docs.oracle.com/javase/7/docs/api/java/lang/Class.html)
        - [ClassLoader](https://docs.oracle.com/javase/7/docs/api/java/lang/ClassLoader.html)
        - [ClassValue](https://docs.oracle.com/javase/7/docs/api/java/lang/ClassValue.html)
        - [Compiler](https://docs.oracle.com/javase/7/docs/api/java/lang/Compiler.html)
        - [Double](https://docs.oracle.com/javase/7/docs/api/java/lang/Double.html)
        - [Enum](https://docs.oracle.com/javase/7/docs/api/java/lang/Enum.html)
        - [Float](https://docs.oracle.com/javase/7/docs/api/java/lang/Float.html)
        - [InheritableThreadLocal](https://docs.oracle.com/javase/7/docs/api/java/lang/InheritableThreadLocal.html)
        - [Integer](https://docs.oracle.com/javase/7/docs/api/java/lang/Integer.html)
        - [Long](https://docs.oracle.com/javase/7/docs/api/java/lang/Long.html)
        - [Math](https://docs.oracle.com/javase/7/docs/api/java/lang/Math.html)
        - [Number](https://docs.oracle.com/javase/7/docs/api/java/lang/Number.html)
        - [Object](https://docs.oracle.com/javase/7/docs/api/java/lang/Object.html)
        - [Package](https://docs.oracle.com/javase/7/docs/api/java/lang/Package.html)
        - [Process](https://docs.oracle.com/javase/7/docs/api/java/lang/Process.html)
        - [ProcessBuilder](https://docs.oracle.com/javase/7/docs/api/java/lang/ProcessBuilder.html)
        - [ProcessBuilder.Redirect](https://docs.oracle.com/javase/7/docs/api/java/lang/ProcessBuilder.Redirect.html)
        - [Runtime](https://docs.oracle.com/javase/7/docs/api/java/lang/Runtime.html)
        - [RuntimePermission](https://docs.oracle.com/javase/7/docs/api/java/lang/RuntimePermission.html)
        - [SecurityManager](https://docs.oracle.com/javase/7/docs/api/java/lang/SecurityManager.html)
        - [Short](https://docs.oracle.com/javase/7/docs/api/java/lang/Short.html)
        - [StackTraceElement](https://docs.oracle.com/javase/7/docs/api/java/lang/StackTraceElement.html)
        - [StrictMath](https://docs.oracle.com/javase/7/docs/api/java/lang/StrictMath.html)
        - [String](https://docs.oracle.com/javase/7/docs/api/java/lang/String.html)
        - [StringBuffer](https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuffer.html)
        - [StringBuilder](https://docs.oracle.com/javase/7/docs/api/java/lang/StringBuilder.html)
        - [System](https://docs.oracle.com/javase/7/docs/api/java/lang/System.html)
        - [Thread](https://docs.oracle.com/javase/7/docs/api/java/lang/Thread.html)
        - [ThreadGroup](https://docs.oracle.com/javase/7/docs/api/java/lang/ThreadGroup.html)
        - [ThreadLocal](https://docs.oracle.com/javase/7/docs/api/java/lang/ThreadLocal.html)
        - [Throwable](https://docs.oracle.com/javase/7/docs/api/java/lang/Throwable.html)
        - [Void](https://docs.oracle.com/javase/7/docs/api/java/lang/Void.html)

## Reference

> 
>
