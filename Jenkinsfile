pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                sh "./gradlew clean assembleDebug"
                
                cucumberSlackSend channel:'Teddy Lin'
            }
        }
    }
    post {
        always {
            archiveArtifacts artifacts: 'app/build/outputs/apk/debug/*.apk', fingerprint: true
        }
    }
}
