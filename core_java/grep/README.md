# Introduction
This application is a simplified JAVA `GREP` program that searches for a regex pattern recursively in a given directory and reports the matched lines in the output file.

To finish this application, I first understood how `Maven` build and manage Java dependencies and build lifecyles. Also `intelliJ` is a strong IDE that insists me with managing the source codes. Last by not least, I understood the new feature introduced in JAVA8 -- `Stream` which contains classes for processing sequences of elements.

# Usage
This program searches for a text pattern recursively in a given directory, and outputs matched lines to a file. The app takes three arguments:
```
USAGE: JavaGrep regex rootPath outFile
- regex: a special text string for describing a search pattern
- rootPath: root directory path
- outFile: output file name
```
Demo
```
Search '(.*)import(.*)' pattern from './grep/src' folder recursively and output the result to '/tmp/grep.out' file. This will give a list of Java libraries this project rely on.
Program arguments '(.*)import(.*) ./grep/src /tmp/grep.out'
```

# Pseudocode
```
matchedLines = []
for file in listFilesRecursively(rootPath):
    for line in readLines(file):
        if containsRegex(line):
            matchedLines.add(line)
    writeToOutFile(matchedLines)
```

# Performance Issue
In this project, the processed file will first be loaded into RAM and then searched for the pattern line by line. In this case, a larger file may cause overflow in the memory and thus crash the whole process.

To furthermore deal with this problem, I plan to preprocess the target file at the first step by looking into the size of file. If certain threshold is reached, I will break the file into several partial paragraphs and look inside these parts one by one. Meanwhile, I won't forget to search patterns over 'connections' between parts.

# Improvement
1. Introduce more options in this project to behave more similar to the real Linux 'GREP' program. Some examples are provided below:
```
-i: Ignore uppercase vs. lowercase.
-v: Invert match.
-c: Output count of matching lines only.
-l: Output matching files only.
-n: Precede each matching line with a line number.
-b: A historical curiosity: precede each matching line with a block number.
-h: Output matching lines without preceding them by file names.
-s: Suppress error messages about nonexistent or unreadable files.
-o: Output the matched parts of a matching line.
```
2. Handle a larger file that may overflow the RAM by breaking down this file into several smaller parts. This topic is already covered in the Performance part.
3. Speed up the searching process by leveraging multi-threading feature in Java so that we can deal with different files in parallel.

