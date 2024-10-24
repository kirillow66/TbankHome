plugins {
    id("java")
}

group = "ru.tbank.edu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.springframework.boot:spring-boot-starter-web:2.6.3")
    implementation("org.springframework.boot:spring-boot-starter-aop:2.6.3")
    implementation("org.slf4j:slf4j-api:1.7.32")
    implementation("ch.qos.logback:logback-classic:1.2.6")
}
tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
tasks.test {
    useJUnitPlatform()
}