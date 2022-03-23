def call(Map pipelineParams) {

    pipeline {
        agent any

        
      environment {
    
      GIT_REPO_URL = "${pipelineParams.GIT_REPO_URL}"
      GIT_BRANCH = "${pipelineParams.GIT_BRANCH}"
      SERVER_IP = "${pipelineParams.SERVER_IP}"
    
    
     
    }



        stages {
         

        
        
        stage('Source') {
        steps {
          sh "printenv | sort"
          sh "rm -rf * && rm -rf .git"
          git branch: "${env.GIT_BRANCH}", url:  "${env.GIT_REPO_URL}"
        }
        }

        stage('VALIDATE SERVER is RUNNING') {
        steps {
          script {
            // sleep for 30 seconds
            sleep 5
            // TODO: Below command doesnt makes sense
            sh '''
            #!/bin/bash
            echo $SERVER_IP
            echo "/dev/udp/$SERVER_IP/6001"
            echo > /dev/udp/$SERVER_IP:6001 && echo "Port is open"
            #nc -w 30 -v $SERVER_IP 6001 </dev/null; echo $?
            '''
          }
        }
      }
        
        
        


}

}
    
}
