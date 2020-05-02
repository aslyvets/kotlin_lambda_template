import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.aslyvets"
version = "0.0.1-SNAPSHOT"


java.sourceCompatibility = JavaVersion.VERSION_11

plugins {
    idea
    kotlin("jvm") version "1.3.72"
}

repositories {
    mavenCentral()
    jcenter {
        content {
            includeGroup("org.jetbrains.kotlinx")
        }
    }
}

dependencies {
    implementation("com.amazonaws:aws-lambda-java-core:1.2.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.6.2")
}

tasks.withType<Test> {
    outputs.cacheIf { true }
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}
