# Question

- 자바에서 동기/비동기 처리할 때 어떤 방법을 사용하는지 설명 부탁드립니다.

---

# Answer

- 동기 처리
    - Java에서의 동기 처리는 일반적인 메소드 호출을 통해 동작합니다.
    - “호출된” 메소드의 동작을 끝내기 까지 “호출한” 메소드는 대기합니다.
- 비동기 처리
    - Java에서 비동기 처리는 몇가지 방법이 있습니다.
    - 가장 초기적인 방법으로 `Thread` 와 `Runnable` 객체를 통해 구현됩니다.
    
    ```sql
    # Runnable 객체에 작업을 정의.
    Runnable task = () -> System.out.println("비동기 작업 실행");
    # 정의된 작업을 Thread에 전달해서 실행한다. 
    Thread thread = new Thread(task);
    thread.start();
    ```
    
    - `Thread` 와 `Runnable` 을 통한 비동기는 요청마다 Thread를 추가하는 단점을 갖고 있습니다.
    - `ExecutorService`는 미리 준비된 thread pool을 통해 비동기 작업을 효율적으로 관리할 수 있는 인터페이스 입니다.
    
    ```sql
    ExecutorService executor = Executors.newSingleThreadExecutor();
    # execute()는 Runnable 객체를 받아서 작업을 실행합니다. 
    executor.execute(() -> System.out.println("execute()를 통한 비동기 작업"));
    # shutdown()은 작업을 중지하기 위해 사용됩니다.
    executor.shutdown();
    
    # submit()은 작업의 결과를 나타내는 Future 객체를 반환합니다. 
    Future<String> future = executor.submit(() -> {
        Thread.sleep(1000);
        return "submit() 결과";
    });
    ```
    
    - `CompletableFuture`는 함수형 프로그래밍 패턴을 사용할 수 있고, 비동기 작업을 간결화하며, 여러 기능의 추상화를 제공합니다.
    
    ```sql
    CompletableFuture.supplyAsync(() -> {
        return "CompletableFuture 비동기 작업";
    }).thenApply(result -> {
        return result.toUpperCase();
    }).thenAccept(System.out::println);
    
    ```
    

### Summery

위에서 설명한 비동기의 구현은 Java에서 사용할 수 있는 방식이고, Java 8 버전 이후로 Flow API 등 새로운 인터페이스가 추가 되었다고 합니다. 

또한, Spring에서 비동기를 쉽게 적용하기 위한 어노테이션등은 직접 학습해보시기 바랍니다. 

## Reference

> 
> 

[JAVA 비동기 프로그래밍의 전반적인 이해: Runnable에서 CompletableFuture까지](https://w55ng.com/entry/Java-%EB%B9%84%EB%8F%99%EA%B8%B0-%EA%B8%B0%EC%B4%88-Callback-Future)
