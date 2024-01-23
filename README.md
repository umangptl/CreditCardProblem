# Credit card problem - individual project 

## Run the Program

To run the Java program, follow these instructions:

1. **Compile the Code:**
   Open your terminal and navigate to the directory containing the Java source code. Compile the code: file `main` is the main class.
2. **Run the Code:** you will see a print statement to guide `"Enter path e.g. /Desktop/creditcards.json"`
3. **Enter the path of the file:** you can enter the path of the file you want to read and process. The program will read the file and generate a new file with the same name and extension in the same directory. The new file will contain the credit card numbers and their validity status. I am using `System.getProperty("user.home");` so you can enter the path from the home directory. e.g. `/Desktop/creditcards.json
4. **Check for error** if you enter the wrong path or the file is not found/compatible  you will see an error message and the program will exit.
5. **Check the output:** you can check the output file in the same directory as the input file. (inputfilename`-output`extension


### Design patterns used
* Factory pattern for credit card objects.
* Strategy pattern for file handling.

### Problems
* The primary problem was figuring out the card type, so I implemented an if-else statement within the credit card creation function. inside the `CardFactoryImp` class to make an appropriate type of Credit card.

* The second problem was how to figure out the file type and handle it so, I decided the best way to tackle that was to use a strategy pattern and to identify the type of file I have if else statement in the class `FileFormatDetector` to check the file extension and if XML, CSV, and JSON and using the appropriate strategy to handle that file type.

Both design patterns are implemented in a way that we can add more different types of cards and file types.
For credit cards Create a subclass and edit the CardFactoryImp to validate the card type and make the right type of card (basically define its rules to verify the type)
For file type make a class to handle that file from reading to generating file and writing the file and edit the FileFormatDetector class to detect the file extension and follow the right path to handle it.

**Factory Design** 
primary design pattern, The CardFactory interface, and its implementation (CardFactoryImp) follow this pattern. It provides an abstract way to create credit card instances based on the card number and type. This pattern allows for encapsulating the card creation process and centralizing it in the CardFactoryImp class.

**Strategy Design**
secondary design pattern, FileHandlingStrategy interface and its concrete implementations (XMLFileHandlingStrategy, JSONFileHandlingStrategy, CSVFileHandlingStrategy) follow the Strategy Pattern. This pattern defines a family of interchangeable algorithms for reading and processing files.

### Consequences of these patterns

#### Factory
*Pros*
* Allows for a consistent way to create objects with the same interface, enhancing code maintainability and flexibility.
* Encapsulates object creation, making it easy to change or extend the object creation process without affecting the client code (open-closed principle).
* Promotes loose coupling between the client code and the objects being created

*Cons*
* It can be difficult to maintain if the factory class is not well designed.
* It can sometimes lead to a proliferation of factory classes, making the codebase more complex
* With few concrete classes, the overhead of implementing a factory might be considered unnecessary.

#### Strategy
*Pros*
* It allows clients to choose from different algorithms (strategies) for reading and processing files without modifying the client code.
* It promotes the "Open-Closed Principle," as new file-handling strategies can be added without altering existing code.
* It promotes loose coupling between the client code and the file-handling strategies.

*Cons*
* Each strategy might need its separate class, potentially leading to a large number of classes in the codebase.
* The choice of strategies at runtime might add some performance overhead compared to a more statically defined approach
* The client must be aware of the different strategies and how to use them.

## Class Diagram of the whole program
<img width="650" alt="screenshot" src="https://github.com/umangptl/CreditCardProblem/blob/main/diagram.png">


