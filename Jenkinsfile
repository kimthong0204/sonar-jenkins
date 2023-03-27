pipeline{
    agent any
    tools {
        maven 'maven-3.8.6'
    }
    node('build') {
        env.JAVA_HOME="${tool 'JDK17'}"
        env.PATH="${env.JAVA_HOME}/bin:${env.PATH}"
        sh 'java -version'
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