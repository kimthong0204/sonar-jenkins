pipeline{
    agent any

    stages{
        stage('Scan') {
            steps{
                withSonarQubeEnv(installationName: 'SonarQubeScanner') {
                    sh './mvw clean verify'
                }
            }
        }
    }
}