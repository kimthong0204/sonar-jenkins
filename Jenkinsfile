pipeline{
    agent any
    tools {
        maven 'maven-3.8.6'
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