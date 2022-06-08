# Read Me First
The cml-url-handle is responsible for add, remove and export URL with their social scores

# Getting Started

### Setup instructions
1. Install Java 17
2. Set Java into user PATH

# Run application
Execute **cml-url-handle-0.0.1-SNAPSHOT.jar**

#### Linux, Windows, MacOS
1. `java -jar cml-url-handle-0.0.1-SNAPSHOT.jar`

# Use application
### To add a new URL
    ADD https://www.google.com,10

### To remove a URL
    REMOVE https://www.google.com

### To export existing URL
    EXPORT

# Build application
If necessary you can build the application again, running the command:
    
    mvn clean install