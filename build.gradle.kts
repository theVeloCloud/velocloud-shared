plugins {
    kotlin("jvm") version "2.3.0"
    `maven-publish`
}

group = "de.snenjih.velocloud"
version = "3.0.5"

repositories {
    mavenLocal()
    mavenCentral()
    maven {
        name = "velocloud-snapshots"
        url = uri("https://repo.snenjih.de/snapshots")
    }
    maven {
        name = "velocloud-releases"
        url = uri("https://repo.snenjih.de/releases")
    }
}

dependencies {
    compileOnly("com.google.code.gson:gson:2.13.2")
    compileOnly("de.snenjih.velocloud:proto:$version")
}

kotlin {
    jvmToolchain(21)
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {

            artifact(tasks.jar.get())

            pom {
                name.set("velocloud-shared")
                description.set("VeloCloud shared interfaces and types")
                url.set("https://github.com/theVeloCloud/velocloud")

                licenses {
                    license {
                        name.set("Apache License 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0")
                    }
                }
                developers {
                    developer {
                        name.set("Mirco Lindenau")
                        email.set("mirco.lindenau@gmx.de")
                    }
                }
                scm {
                    url.set("https://github.com/theVeloCloud/velocloud")
                    connection.set("scm:git:https://github.com/theVeloCloud/velocloud.git")
                    developerConnection.set("scm:git:https://github.com/theVeloCloud/velocloud.git")
                }
            }
        }
    }

    repositories {
        maven {
            name = "reposilite"
            url = uri(
                if (version.toString().endsWith("-SNAPSHOT"))
                    "https://repo.snenjih.de/snapshots"
                else
                    "https://repo.snenjih.de/releases"
            )
            credentials {
                username = System.getenv("REPOSILITE_USER") ?: ""
                password = System.getenv("REPOSILITE_SECRET") ?: ""
            }
        }
    }
}
