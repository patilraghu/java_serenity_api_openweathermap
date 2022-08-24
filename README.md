# API Testing using Java/Serenity/BDD Framework

Comments...

### Prerequisites to run test
* Install Java JDK 8
* Install Gradle 7.2 (Optional)
* Run java -version and gradle -version to see if it is set to right version
* Set Paths if command not found

### How to run test
* Go to project root directory and run following
* gradle clean test aggregate //To run all tests
* gradle clean test aggregate -Dcucumber.filter.tags="@negative" // To run specific test

### Test report
* Open index.html from project root/target/site/serenity folder in browser
