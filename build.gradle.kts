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
    implementation("aws.sdk.kotlin:dynamodb:1.4.7")
    testImplementation(kotlin("test"))
    testImplementation("com.amazonaws:DynamoDBLocal:2.5.4")
}

tasks.test {
    useJUnitPlatform()
}