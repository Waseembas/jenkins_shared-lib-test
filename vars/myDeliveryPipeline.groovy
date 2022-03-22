def call(Map pipelineParams) {

    pipeline {
        agent any

        
      environment {
    
      GIT_REPO_URL = "${pipelineParams.GIT_REPO_URL}"
      GIT_BRANCH = "${pipelineParams.GIT_BRANCH}"
    
     
    }



        stages {
         

        
        
        stage('Source') {
        steps {
          sh "printenv | sort"
          sh "rm -rf * && rm -rf .git"
          git branch: "${env.GIT_BRANCH}", url:  "${env.GIT_REPO_URL}"
        }
      }


}

}
    
}
