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
        stage('Scan Email') {
                    steps{
                        emailext(attachLog: true, body: 'This is the extended email test', subject: 'This is the extended email test subject', to: 'kthong0204@gmail.com')
                    }
                }
    }
}