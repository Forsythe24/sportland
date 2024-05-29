import java.util.*

plugins {
    id("java")
    id("org.springframework.boot") version "2.7.17"
    id("application")
    id("org.liquibase.gradle") version "2.2.0"
    id("jacoco")
}

apply(plugin = "io.spring.dependency-management")

application {
    mainClass.set("itis.solopov.Main")
}

group = "itis.solopov"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-freemarker")
    implementation("org.springframework.boot:spring-boot-starter-mail")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.security:spring-security-taglibs:${properties["springSecurityVersion"]}")
    implementation("org.projectlombok:lombok:1.18.22")
    implementation("org.projectlombok:lombok:1.18.22")
    annotationProcessor("org.hibernate:hibernate-jpamodelgen:${properties["hibernateVersion"]}")
    implementation("org.postgresql:postgresql:42.7.2")
    implementation("org.liquibase:liquibase-core:4.20.0")
    liquibaseRuntime("org.liquibase:liquibase-core:4.20.0")
    liquibaseRuntime("org.postgresql:postgresql:42.7.2")
    liquibaseRuntime("info.picocli:picocli:4.6.3")
    implementation("org.apache.tomcat:tomcat-jsp-api:10.1.20")
    implementation("javax.servlet.jsp:jsp-api:2.1")
    implementation("javax.mail:javax.mail-api:1.6.2")

    implementation ("org.apache.commons:commons-lang3:3.12.0")

    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
    implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")

    implementation("com.github.mifmif:generex:1.0.1")

    developmentOnly("org.springframework.boot:spring-boot-devtools")

    implementation("com.google.code.gson:gson:2.10.1")

    implementation("com.squareup.okhttp3:okhttp:5.0.0-alpha.3")

    implementation("org.springdoc:springdoc-openapi-ui:1.8.0")
    implementation("dev.gustavoavila:java-android-websocket-client:2.0.2")

    implementation("org.webjars:stomp-websocket:2.3.4")
    implementation("org.webjars:sockjs-client:1.5.1")
    implementation("org.webjars:jquery:3.6.0")
    implementation("org.webjars:bootstrap:4.6.0")
    implementation("org.webjars:webjars-locator-core:0.46")
    implementation("org.springframework.boot:spring-boot-starter-websocket")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")

    implementation ("org.json:json:20211205")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    testImplementation("org.mockito:mockito-junit-jupiter:5.2.0")
}

tasks.withType<Test> {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(false)
        csv.required.set(false)
        html.outputLocation.set(layout.buildDirectory.dir("jacocoHtml"))
    }
}

jacoco {
    toolVersion = "0.8.8"
    reportsDirectory.set(layout.buildDirectory.dir("customJacocoReportDir"))
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            limit {
                minimum = "0.5".toBigDecimal()
            }
        }
    }
}


var props = Properties()
props.load(file("src/main/resources/liquibase.properties").inputStream())

liquibase {
    activities.register("main") {
        arguments = mapOf(
                "changeLogFile" to props.get("change-log-file"),
                "url" to props.get("url"),
                "username" to props.get("username"),
                "password" to props.get("password"),
                "driver" to props.get("driver-class-name")
        )
    }
}


