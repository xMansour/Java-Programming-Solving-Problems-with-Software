# Java Programming: Solving Problems with Software
This is the first course out of nine programming courses provided by [coursera](https://www.coursera.org/) and are listed [here](https://github.com/ForrestKnight/open-source-cs)

You can find this course [here](https://www.coursera.org/learn/java-programming)


## Week 1
In this week you will learn about programming in general and the syntax of java. You will also try to get the perimeter of different shapes using code.

You will also learn about the 7-Steps of solving issues.

#### Seven Steps 
    1. Work example by hand
    2. Write down what you did
    3. Find patterns
    4. Check by hand
    5. Translate to code
    6. Run test cases
    7. Debug failed test cases


## Week 2
In this week you will learn about processing genes in the dna. You will be able to count, print and store the valid genes in a string or a file.

You will also learn about the following concepts.

#### Short Circuit Evaluation

```java
if(x < y && y < z)
    ....
```
if x < y is false then there is no need to evaluate the second part y < z since the logical AND operation needs both parameters to be true.

```java
if(x < y || x < z)
    ....
```
if x < y is true then there is no need to evaluate the second part x < z since the logical OR operation needs only one parameter to be true.


#### Seperation of concern
Try to seperate your code into multiple reusable methods.


## Week 3
In this week you will learn about CSV(Comma seperated values) Files and processing them using an open source java libarary called 
[Apache Commons CSV](https://commons.apache.org/proper/commons-csv/).


## Week 4 
In this week you will create a project to process large CSV Files containing a lot of data about the united state childbirth names and how popular these names are.