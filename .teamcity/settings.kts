import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2019_2.vcs.GitVcsRoot

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2020.1"

project {

    vcsRoot(Github)

    buildType(Build)
}

object Build : BuildType({
    name = "build"

    vcs {
        root(Github)
    }

    steps {
        maven {
            name = "package"
            goals = "clean compile package"
        }
    }
})

object Github : GitVcsRoot({
    name = "github"
    url = "https://github.com/nickbob/spring-security-app.git"
    branch = "master"
    authMethod = password {
        userName = "nickbob"
        password = "credentialsJSON:63761a98-db3f-45d2-997a-23fd1663c277"
    }
})
