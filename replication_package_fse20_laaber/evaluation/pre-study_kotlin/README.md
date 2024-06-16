# Source Code Parser

Our source code parser relies on the open-source library [_bencher_](https://github.com/chrstphlbr/bencher).
As this library is not available on maven central, we need to clone it, perform minor modifications, and publish it to the local maven repository.
For this we need to follow the subsequent steps:

1. Use version with git commit hash `97f859264dcd2005c27c75e0b67fa424defa7b01`

2. Replace the following code in the method `config(bench: Benchmark)` of the class `ch.uzh.ifi.seal.bencher.execution.ConfigBasedConfigurator`

```kotlin
return if (valid(c)) {
    Either.right(c)
} else {
    Either.left("Invalid configuration for benchmark ($bench) and provided default/class/benchmark configurations")
}
```

with

```kotlin
return Either.right(c)
```

3. Run 

```bash
./gradlew publishToMavenLocal
``` 