pipeline{
    agent any
    tools {
        maven 'maven-3.8.6'
        jdk 'JAVA_HOME'
    }
    stages{
        stage('Scan') {
            steps{
                withSonarQubeEnv(installationName: 'SonarQubeScanner') {
                    sh 'mvn clean verify'
                }
            }
        }
    }
}