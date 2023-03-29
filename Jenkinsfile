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
                    sh 'mvn clean install'
                }
            }
        }
    }
    post{
        always{
            emailext to : "kthong0204@gmail.com",
            subject: "Test Email",
            body: "Test",
            attachLog: true
        }
        failure{
            emailext to : "kthong0204@gmail.com",
            subject: "jenkins build:${currentBuild.currentResult}: ${env.JOB_NAME}",
            body: "${currentBuild.currentResult}: Job ${env.JOB_NAME}\nMore Info can be found here: ${env.BUILD_URL}"
        }
    }
}