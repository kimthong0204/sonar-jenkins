pipeline{
    agent any

    stages{
        stage('Scan') {
            withSonarQubeEnv(installationName: 'SonarQubeScanner') {
                sh './mvw clean verify'
            }
        }
    }
}