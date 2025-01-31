plugins {
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.serialization") version "2.0.21"
    kotlin("plugin.spring") version "2.0.0"
    id("org.springframework.boot") version "3.4.2"

}

group = "net.pippah"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:3.4.2")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    implementation("aws.sdk.kotlin:dynamodb:1.4.7")
    testImplementation(kotlin("test"))
    testImplementation("com.amazonaws:DynamoDBLocal:2.5.4")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.4.2") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
}

tasks.test {
    useJUnitPlatform()
}