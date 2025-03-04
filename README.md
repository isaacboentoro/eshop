# Reflections
## Name: Isaac Jesse Boentoro | NPM: 2306256362

# Assignment 1

## Reflection 1
I have followed proper coding standards while implementing the new features, ensuring that variable and function names are meaningful and written in camelCase. Additionally, I have used clear and concise names for classes and methods, making the code more readable and maintainable. Each function is accompanied by short but informative comments that describe its purpose, helping other developers understand the logic more easily. I have also adhered to clean code principles by keeping methods short, focused, and free from unnecessary complexity. Overall, these practices enhance the maintainability, readability, and scalability of the project.

## Reflection 2
I believe that an arbitrary number of tests ensuring full code and feature coverage are needed to verify a program's correctness. Even with 100% test coverage, a program may still contain bugs because coverage alone does not guarantee that all possible edge cases are tested. Tests can trigger lines of code without validating their correctness under different scenarios, which is why well-designed test cases are essential. To improve the effectiveness of tests, it is crucial to include boundary testing, negative testing, and real-world usage scenarios. While high coverage is beneficial, true reliability comes from a combination of thoughtful test case design and continuous testing improvements.

I think that creating a new class for that one test is redundant. A better approach would be to incorporate testing the number of products in the product list while testing creating multiple products, ensuring no functions are created twice and removing boilerplate code. 

# Assignment 2
 
# Reflection 1
After implementing Sonarcloud integration and doing an initial scan, a couple of issues showed up. Most were just mantainability and readability issues, with no critical security issues. However, they were simple enough to fix, such as replacing decorators with constructor injection in ProductController.java. I think my CI/CD pipeline has indeed met the definition of Continuous Integration and Continuous Deployment. When I push to my main branch, it is immediately tested and scanned for code smells (continuous integration) and deployed to Koyeb (continuous deployment).

# Assignment 3

# Reflection 1
I applied the Single Responsibility Principle by separating CarController from ProductController into its own class, CarController.java. These classes are able to be extended, but not modified, thus fulfilling the Open-Closed princple. Since there are no parent-child classes, the Liskov substitution principle is not applied. Separating the service implementations (ProductServiceImpl, CarServiceImpl) also helps fulfill the Interface Segregation Principle. 

Applying SOLID principles makes my code more mantainable and easier to understand, because each class has one specific purpose. For example, ProductController was cluttered with car-related mappings before it was separated to CarController.java. 

Not applying SOLID principles could make the project clutteerd, for example with many dependencies in the service implementation if you combined both files. 

# Assignment 4

# Reflection 1
I think that the TDD workflow has benefited me. The tests are strucutred, and have coverage of both happy and error paths, with mocking used to isolate the service from its dependencies. It also follows the FIRST Principles analysis:

 - Fast: tests use mocks instead of actual database operations, without any sleep or delay that would slow execution
 - Independent: Each test method focuses on a single behaviour, and do not depend on each other's execution, and fresh data is created in the BeforeEach setup
 - Repeatable: tests use mocked repositories, avoiding third party dependencies. Fixed test data means consistent results
 - Self-validating: Clear assertions determine pass or fail. 
 - Thorough and timely: Test has high code coverage, but could have some more for corner cases, and use parameterized tests. 