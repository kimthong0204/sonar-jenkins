// pipeline{
//     agent any
//     tools {
//         maven 'maven-3.8.6'
//         jdk 'JAVA_HOME'
//     }
//     options {
//         buildDiscarder(logRotator(numToKeepStr: '5'))
//     }
//
//     stages{
//     stage('SCM') {
//         checkout scm
//       }
//       stage('SonarQube Analysis') {
//         def mvn = tool 'Default Maven';
//         withSonarQubeEnv() {
//           sh "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=test"
//         }
//       }
// //         stage('Scan') {
// //             steps{
// //                 withSonarQubeEnv(installationName: 'sq1') {
// //                     sh 'mvn clean org.sonarsource.scanner.maven:sonar-maven-plugin:3.9.0.2155:sonar'
// //                 }
// //             }
// //         }
//     }
// }

node {
  stage('SCM') {
    checkout scm
  }
  stage('SonarQube Analysis') {
    def mvn = tool 'Default Maven';
    withSonarQubeEnv() {
      sh "${mvn}/bin/mvn clean verify sonar:sonar -Dsonar.projectKey=test"
    }
  }
}