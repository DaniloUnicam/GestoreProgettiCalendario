plugins {
    id("java")
    kotlin("jvm")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation(kotlin("stdlib-jdk8"))

    implementation("org.hibernate:hibernate-core:6.1.2.Final")
    implementation("com.h2database:h2:2.1.214")
    implementation("javax.persistence:javax.persistence-api:2.2")
}


tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}