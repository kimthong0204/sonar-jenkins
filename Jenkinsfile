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
                    sh 'mvn clean verify sonar:sonar -Dsonar.host.url=http//localhost:9000 -Dsonar.login=squ_781ded19bbe4335f1bf1daa581d4fb9b768eeb25 -Dsonar.projectKey=SonarQubeScanner'
                }
            }
        }
    }
}