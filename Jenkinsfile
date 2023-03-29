pipeline{
    agent any
    tools {
        maven 'maven-3.8.6'
        jdk 'JAVA_HOME'
    }
    options {
        buildDiscarder(logRotator(numToKeepStr: '5'))
    }
    stages{
        stage('Scan') {
            steps{
                withSonarQubeEnv(installationName: 'sq1') {
                    sh 'mvn clean instal'
                }
            }
        }
    }
    post{
        success{
            emailext to : "kthong0204@gmail.com",
            subject: "Success Pipeline: ${currentBuild.fullDisplayName}",
            body: "All is good with ${env.BUILD_URL}",
            attachLog: true
        }
        failure{
            emailext to : "kthong0204@gmail.com",
            subject: "Failed Pipeline: ${currentBuild.fullDisplayName}",
            body: "Something is wrong with ${env.BUILD_URL}"
        }
    }
}