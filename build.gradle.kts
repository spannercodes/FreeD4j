plugins {
    id("java")
}

group = "dev.spnr"
version = "0.3.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.8.1");
}

tasks {
    test {
        useJUnitPlatform()
    }
}