pipeline {
    agent any
    stages {
        stage('SCM') {
            steps {
                git changelog: false, credentialsId: 'jenkinsChatapp', url: 'https://github.com/kunalkale98/Spring-Boot-API.git'
            }
        }
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Deploy') {
            steps {
                sh 'rsync -r -e "ssh -i /home/ubuntu/kunal_123.pem" /var/lib/jenkins/workspace/Java_App/target/SpringBootDemoProject-0.0.1-SNAPSHOT.jar ubuntu@10.1.0.45:/home/ubuntu/'
                sh 'rsync -r -e "ssh -i /home/ubuntu/kunal_123.pem" /var/lib/jenkins/workspace/Java_App/Java_App.service ubuntu@10.1.0.45:/home/ubuntu'
                sh 'rsync -r -e "ssh -i /home/ubuntu/kunal_123.pem" /var/lib/jenkins/workspace/Java_App/start.sh ubuntu@10.1.0.45:/home/ubuntu'
                sh 'ssh -i /home/ubuntu/kunal_123.pem ubuntu@10.1.0.45 "bash /home/ubuntu/start.sh"'
            }
        }
    }
}
