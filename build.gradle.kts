plugins {
    id("java")
    id("application")
    id("org.openjfx.javafxplugin") version "0.1.0"
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
    mainClass.set("JTime.Application.MainGUI")
}

// Uses Java 21
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}
// Dependencies configuration
dependencies {
    implementation("jakarta.annotation:jakarta.annotation-api:2.1.1")

    // --- Hibernate & Database ---
    implementation("org.hibernate.orm:hibernate-core:6.4.4.Final")
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
    implementation("jakarta.enterprise:jakarta.enterprise.cdi-api:4.0.1")

    implementation("com.h2database:h2:2.2.224")
    // --- JavaFX ---
    implementation("org.openjfx:javafx-controls:21.0.2")
    implementation("org.openjfx:javafx-fxml:21.0.2")
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

