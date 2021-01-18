# Benchmark

This is a standalone Android Studio project to benchmark performance of the Json parser. While the root `apollo-android` project can be opened with IntelliJ, this one requires Android Studio.

It's not included in the composite build so it's easy to swap the Apollo version but it reuses the dependencies from the main project and declares OSS Snapshots and mavenLocal() as fallback repositories (in that order) so you can use `publishToMavenLocal` and snapshots easily.

## Running the tests

You can run the tests from Android Studio by clicking the "run" icon in the gutter next to `class Benchmark`. This will print the results in the `Run` window of Android Studio

## Current results:

Run on a Pixel 3 XL. Feel free to update/commit new results and we can get the history using `git annotate`

```
benchmark:    13,264,949 ns Benchmark.moshi
benchmark:    14,958,074 ns Benchmark.apollo
benchmark:    23,819,793 ns Benchmark.apolloParseAndNormalize
benchmark:    64,193,652 ns Benchmark.apolloBatchCache
benchmark:   179,012,778 ns Benchmark.apolloStreamCache
benchmark:   222,460,595 ns Benchmark.apolloReadCache
```

```
benchmark:   190,612,154 ns SQLarge.readChunked1
benchmark:   112,064,595 ns SQLarge.readChunked2
benchmark:    63,486,204 ns SQLarge.readChunked4
benchmark:    39,041,775 ns SQLarge.readChunked8
benchmark:    26,560,992 ns SQLarge.readChunked16
benchmark:    20,150,315 ns SQLarge.readChunked32
benchmark:    17,027,189 ns SQLarge.readChunked64
benchmark:    15,319,585 ns SQLarge.readChunked128
benchmark:    15,455,626 ns SQLarge.readChunked256
benchmark:    15,142,398 ns SQLarge.readChunked512
benchmark:    14,664,428 ns SQLarge.readChunked1024
```

?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?