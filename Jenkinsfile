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
                    sh 'mvn clean verify -D sonar.login=squ_6a8710329c4d384a4451159d6e557b9eda79f9d3'
                }
            }
        }
    }
}