plugins {
    id("java")
}

group = "br.com.dio"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.liquibase:liquibase-core:4.29.1")
    implementation("mysql:mysql-connector-java:8.0.33")
    implementation("com.h2database:h2:2.2.224")
    implementation("org.projectlombok:lombok:1.18.34")

    annotationProcessor("org.projectlombok:lombok:1.18.34")
}

tasks.test {
    useJUnitPlatform()
}

tasks.register("run") {
    dependsOn("build")
    doLast {
        exec {
            commandLine("java", "-cp", "build/libs/asdasd-1.0-SNAPSHOT.jar", "br.com.dio.Main")
        }
    }
}