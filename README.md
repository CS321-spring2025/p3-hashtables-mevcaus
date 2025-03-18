# Project #3: Experiments with hashing

* Author: Mevludin Causevic
* Class: CS321 Section #2
* Semester: Spring 2025

## Overview

This project implements a hash table using two different collision resolution
strategies. The first is linear probing and the second is double hashing. The
program also accepts 3 different data sources, and analyzes the performance
of each approach at various load factors.

## Reflection

This project was fun to work through. Linear probing is very intuitive to me
and implementation was straight forward. Double hashing was also pretty simple.
The results prove that in cases where you have some sort of clustering,
like in language, you end up having a high amount of collisions causing a 
massive downgrade to performance for linear probing. Double hashing is
more efficient in those cases but the Date values table shows that if there
is no clustering in the input, then both methods perform about the same.

This was the first project where I had to utilize the cloud and run my 
program on it. This posed an usual challenge compared to what I'm used to. In
the end I was able to spin up an EC2 instance and clone down my repo and 
transfer all my testing files with scp into the instance and the program 
ran successfully. In the future this will be a way easier task.

## Compiling and Using

To compile use the following command:
```
javac HashtableExperiment.java
```
To run the program, use the following command line:
```
java HashtableExperiment <input_type> <load_factor> [<debug-level]
```

Where:
- `<dataSource>`:
- - 1: random numbers
- - 2: date values as longs
- - 3: word list
- `<loadFactor>` The ratio of objects to table size
- `<debug-level>`:
- - 0: print summary of experiment (default behavior)
- - 1: save the two hash tables to a file at the end
- - 2: print debugging output for each insert

Example:
```
java HashtableExperiment 3 0.7
```

## Results

### Random Numbers Data Source
| Load Factor | Linear Probing (Avg. Probes) | Double Hashing (Avg. Probes) |
|-------------|------------------------------|------------------------------|
| 0.5         | 1.49                         | 1.39                         |
| 0.6         | 1.75                         | 1.53                         |
| 0.7         | 2.18                         | 1.71                         |
| 0.8         | 3.08                         | 2.01                         |
| 0.9         | 5.45                         | 2.55                         |
| 0.95        | 10.32                        | 3.17                         |
| 0.99        | 62.89                        | 4.61                         |

### Random Date Data Source

| Load Factor | Linear Probing (Avg. Probes) | Double Hashing (Avg. Probes) |
|-------------|------------------------------|------------------------------|
| 0.5         | 1.28                         | 1.38                         |
| 0.6         | 1.44                         | 1.66                         |
| 0.7         | 1.60                         | 1.98                         |
| 0.8         | 1.82                         | 2.47                         |
| 0.9         | 2.18                         | 3.08                         |
| 0.95        | 2.70                         | 3.79                         |
| 0.99        | 5.41                         | 5.55                         |

### Word List Data Source

| Load Factor | Linear Probing (Avg. Probes) | Double Hashing (Avg. Probes) |
|-------------|------------------------------|------------------------------|
| 0.5         | 1.60                         | 1.39                         |
| 0.6         | 2.15                         | 1.53                         |
| 0.7         | 3.60                         | 1.72                         |
| 0.8         | 6.71                         | 2.02                         |
| 0.9         | 19.81                        | 2.57                         |
| 0.95        | 110.59                       | 3.19                         |
| 0.99        | 471.67                       | 4.7                          |
## Sources used

CS321 instructors psuedo-code and assistance from the Kount Learning Center

----------

