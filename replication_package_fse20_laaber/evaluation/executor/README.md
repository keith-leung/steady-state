# Executor

The executor handles the execution of JMH benchmarks. As input it takes a csv file (see input.csv)

| Column        | Description           |
| ------------- |:-------------:|
| project | Project name for logging and output filename |
| benchmark | Fully qualified benchmark name for logging, execution and output filename |
| params | String of benchmark parameters for logging, execution and output filename (*) |
| javaPath | Normally just `java` but if another version is used just the path |
| jarFile | Path to the jar file which contains the benchmark |
| arguments | Optional arguments set for execution (**) |

(*) If a benchmark has two JMH Params `variable1` and `variable2` and the instance is executed with  `variable1` equal to `abc` and `variable2` equal to `xyz` the string looks like `variable1=abc&variable2=xyz`
(**) We normally set the number of forks, benchmark mode etc. as CLI flags

Multiple projects (i.e. different jar files) can be executed at the same time as the column jarFile defines source.