# RestAssured Framework
## API / DEPENDENCIES USED
- TestNG - Testing framework
- Jackson - for serialization/deserialization of pojo classes
- AssertJ - fluent assertion for java 
- Lombook - reducing boilerplate code
- RestAssured - api dependency for java testing
- Faker - Automatic data generation dependency
- Sl4j/Logback - java logging api 
- Cucumber - BDD tool for java
- maven-cucumber-reporting - html reports creator

To Execute test 

Navigate to project root directory and run the following commands 
- mvn test - runs the entire rests in the project.
- mvn test -Dcucumber.options="--tags @tagName" - runs a tests with the matching tags
- 