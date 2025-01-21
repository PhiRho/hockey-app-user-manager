plugins {
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.serialization") version "2.0.21"
}

group = "net.pippah"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    implementation(platform("software.amazon.awssdk:bom:2.30.2"))
    implementation("software.amazon.awssdk:dynamodb")
    testImplementation(kotlin("test"))
    testImplementation("com.amazonaws:DynamoDBLocal:2.5.3")
}

tasks.test {
    useJUnitPlatform()
}