# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build & Run Commands

```bash
# Build
./mvnw clean install

# Run the Spring Boot app
./mvnw spring-boot:run

# Run all tests
./mvnw test

# Run a single test class
./mvnw test -Dtest=JavaSpringbootPracticeApplicationTests

# Run a specific test method
./mvnw test -Dtest=ClassName#methodName
```

On Windows, use `mvnw.cmd` instead of `./mvnw`.

## Architecture

This is a **Java 21 + Spring Boot 3.5.7** learning/practice repository. It is not a production application — most code is standalone practice organized by concept, not by feature.

### Three distinct execution modes

**1. Spring Boot web app** (`JavaSpringbootPracticeApplication`) — a runnable web server with:
- `controller/MyController` — two REST endpoints (`/send-email`, `/process`) wired to `EmailService`
- `service/EmailService` — demonstrates sync vs. async patterns (`CompletableFuture.supplyAsync`)
- `MyConfiguration` — commented-out bean for virtual thread executor (intentionally disabled)
- `config/` — Spring bean scope demos (`SingletonBean`, `PrototypeBean`, `AppConfig`)

**2. `AnotherMain`** — a separate entry point that manually boots a Spring `AnnotationConfigApplicationContext` (not the web server) to demonstrate the Singleton-holding-Prototype scope problem.

**3. Standalone `main()` classes** — the bulk of the repo. Each package is a self-contained practice area run by executing its `main()` directly in the IDE. These are never invoked by the Spring context.

### Package map by topic

| Package | Topic |
|---|---|
| `concurrency/completableFutures/` | `CompletableFuture` chaining and composition |
| `concurrency/virtualThreads/` | Java 21 virtual threads |
| `concurrency/parallelStreams/` | Parallel stream pitfalls |
| `concurrency/generalConcepts/` | `Runnable`, `Thread`, `ExecutorService` basics |
| `executorService/` | `ExecutorService` lifecycle (`shutdown`, `shutdownNow`, `awaitTermination`, `invokeAll`, `invokeAny`) |
| `streams/` | Stream creation, intermediate/terminal ops, primitive streams, laziness |
| `lambdaExpressions/` | Lambdas, method references, functional interfaces |
| `optionals/` | `Optional` API |
| `enums/` | Enum patterns: abstract methods, `EnumMap`, switch expressions |
| `sealedClasses/` | Sealed classes + pattern matching in switch |
| `afterJava8/` | Post-Java-8 features: text blocks, `yield`, private interface methods, stream enhancements |
| `designPatterns/` | Singleton, Builder, Adapter, Factory Method |
| `questions/` | Coding problems: sorting, search, string manipulation, LRU cache, plus NeetCode-style problems (groupAnagrams, topKFrequent, maxProfit, valid brackets) |
| `leetProblems/` | LeetCode problems organized by technique: sliding window, two pointers, binary search |
| `dsa/mylinkedlist/` | Custom singly-linked list — implements append, prepend, removeLast, removeFirst, get, set, insert |
| `serializeDeserialize/` | Java native serialization and Jackson JSON (writes `student.ser` to project root as a side effect) |
| `readWriteFiles/` | File I/O |
| `annotations/` | Custom annotation practice |
| `individualConcepts/` | `Comparator` and other isolated topics |

### Key shared utility

`util/Util.sleep(int millis)` — wraps `Thread.sleep`, re-throws as `RuntimeException`. Used throughout concurrency examples to simulate work.

### Conventions in this repo

- `main()` methods use heavy commenting-out of call sites to selectively run individual scenarios — this is intentional, not dead code.
- `questions/` and `leetProblems/` overlap in style; newer problems in `questions/` (e.g., `ArrayThings`) follow NeetCode patterns.

### Dependencies

- **Lombok** — used for `@RequiredArgsConstructor`, `@AllArgsConstructor` on practice model classes
- **spring-boot-devtools** — hot reload during development
- **jackson-datatype-jsr310** — Java 8 date/time support for JSON

### Adding new practice topics

Follow the existing pattern: create a new package under `com.practice.<topic>/`, add a class with a `public static void main(String[] args)` entry point, and run it directly. No wiring into the Spring context is needed unless the topic is specifically about Spring beans.
