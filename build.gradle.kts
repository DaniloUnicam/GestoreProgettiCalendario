plugins {
    id("java")
    id("application")
    id("org.openjfx.javafxplugin") version "0.1.0"
    id("org.jetbrains.kotlin.jvm") version "2.0.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

// Librarys configuration for JavaFX
javafx {
    version = "21.0.2"
    modules = listOf("javafx.controls", "javafx.fxml")
}
// Main class configuration
application {
    mainClass.set("MainGUI")
}
// Uses Java 21
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}
// Dependencies configuration
dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation(kotlin("stdlib-jdk8"))
    // --- Hibernate & Database ---
    // Hibernate 6.x richiede Jakarta Persistence (non javax)
    implementation("org.hibernate.orm:hibernate-core:6.4.4.Final")
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
    implementation("jakarta.enterprise:jakarta.enterprise.cdi-api:4.0.1")

    // Database H2 (ottimo per test locali) o MySQL
    implementation("com.h2database:h2:2.2.224")
    // implementation("com.mysql:mysql-connector-j:8.3.0") // Decommenta se usi MySQL

    // --- Testing ---
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}
// Test configuration
tasks.test {
    useJUnitPlatform()

    testLogging {
        events("failed", "skipped")

        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        showStackTraces = true
        showStandardStreams = true
        showCauses = true
    }
}

