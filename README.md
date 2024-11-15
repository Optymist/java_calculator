# Java Swing Calculator

A simple, interactive calculator built using Java Swing. This calculator application allows users to perform basic arithmetic operations 
(following BODMAS rules) and includes an interactive GUI interface with buttons and a display area. Users can enter calculations using 
both mouse clicks on the buttons and keyboard input (including pressing `Enter` for quick calculation).

## Features

- **Basic Arithmetic Operations**: Supports addition, subtraction, multiplication, division, power and squareroot funcionality.
- **Keyboard Input**: Press `Enter` for calculation, as well as using `+`, `-`, `*`, and `/` keys.
- **Undo Button**: Delete the last character of the input, or navigate in the textfield to where you want to delete and delete
                  the character before your caret position.
- **Color Customization**: Choose a background color for the calculator.
- **Error Handling**: Displays friendly error messages for invalid operations, such as division by zero.

## Getting Started

These instructions will help you set up and run the Java Swing Calculator on your local machine.

### Prerequisites

Make sure you have the following installed:

- **Java Development Kit (JDK)**: Version 11 or later. You can download it from [Oracle's website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or [OpenJDK](https://openjdk.java.net/).
- **Git**: To clone the repository. If you don’t have Git, download it from [Git’s website](https://git-scm.com/).
- **An IDE or Text Editor** (Optional but recommended): An IDE like IntelliJ IDEA or Eclipse can make it easier to run and test the project.

### Running the Calculator

1. Clone the repository:
   ```bash
   git clone https://github.com/Optymist/java_calculator.git

#### Option 1 (standard file navigation)

2. Open the project in your preferred IDE.
3. Locate the Calculator.java file.
4. Right click and run or press the ▶ button next to the class name.

#### Option 2 (running from the command line)

2. Ensure you are in the project directory:
   ```bash
   cd ~/java_calculator/calculator
3. Compile the program:
   ```bash
   javac -d bin src/main/java/org/example/Calculator.java
4. Run the program:
   ```bash
   java -cp bin org.example.Calculator


5. After executing any of these a window should pop up with the calculator's screen evident.

### Testing the Calculator

