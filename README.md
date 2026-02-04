--- REQUIREMENTS ---

1)    Install Java JDK 21.0.9

  winget install EclipseAdoptium.Temurin.21.JDK

2)    Restart terminal, then verify
  java --version
  Expected: 21.0.9 (or any 21.x)

3)    Configure Enviroment Variables to correctly map where Java is installed.
  Press Start, search for "Enviroment variable". Under "Enviroment Variables for %username%":
  
  press New...
  
  Name it as: JAVA_HOME
  
  As value, insert the path where your jdk (version 21.0.9) is installed: C:\Program Files (x86)\Java\jdk-21.0.9

5)    Install Gradle version: 8.14
  Use the Gradle wrapper in the repo
  
  .\gradlew --version

7)    Run the project

.\gradlew run
