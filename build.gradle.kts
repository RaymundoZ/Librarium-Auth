plugins {
    java
    id("org.springframework.boot") version "3.2.5"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "com.raymundo.librarium"
version = "0.0.1"
extra["springCloudVersion"] = "2023.0.1"
extra["jwtVersion"] = "0.12.5"
extra["internalVersion"] = "+"

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/${System.getenv("INTERNAL_REPOSITORY")}")
        credentials {
            username = System.getenv("GITHUB_ACTOR")
            password = System.getenv("PACKAGES_TOKEN")
        }
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("io.jsonwebtoken:jjwt-api:${property("jwtVersion")}")
    implementation("io.jsonwebtoken:jjwt-impl:${property("jwtVersion")}")
    implementation("io.jsonwebtoken:jjwt-jackson:${property("jwtVersion")}")
    implementation("com.raymundo.librarium:internal:${property("internalVersion")}")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}