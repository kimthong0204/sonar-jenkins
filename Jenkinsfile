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
                    sh 'mvn clean org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.1.2184:sonar'
                }
            }
        }
    }
}