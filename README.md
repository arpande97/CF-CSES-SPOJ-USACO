# Competitive Programming Template

This repository contains solutions to various competitive programming problems, using a robust Java template that provides fast I/O operations and utility functions.

## Template Features

### Fast I/O
The template includes a custom `Reader` class that provides fast input operations:
- `nextInt()`: Read next integer
- `nextLong()`: Read next long integer
- `nextDouble()`: Read next double
- `readLine()`: Read next line as string

### Utility Methods
Quick access methods for input/output operations:
- `ni()`: Read next integer
- `nl()`: Read next long
- `nd()`: Read next double
- `ns()`: Read next string

### Output Methods
Buffered output methods for efficient writing:
- `print(String/int/long)`: Print without newline
- `println(String/int/long)`: Print with newline
- `printSp(String/int/long)`: Print with space

### Pair Classes
Template includes utility pair classes for storing tuples:
- `Pii`: Pair of integers
- `Pll`: Pair of long integers
- `Pil`: Pair of integer and long

## Usage

1. Each problem solution should be created in a new file extending this template
2. The main logic should be implemented in the `solve` method
3. Use the provided I/O methods instead of standard Java Scanner/System.out for better performance

### Example Usage

```java
// Reading input
int n = ni();  // reads single integer
int[] arr = new int[n];
for(int i = 0; i < n; i++) {
    arr[i] = ni();
}

// Writing output
println(result);  // prints result with newline
```

## Performance Considerations

- Uses `BufferedWriter` for output operations
- Custom `Reader` class implements efficient input reading
- Pair classes include proper `equals()` and `hashCode()` implementations for use in collections

## Note

This template is specifically designed for competitive programming where:
- Fast I/O operations are crucial
- Memory usage is not a primary concern
- Code needs to be written and executed quickly
