package com.xys

import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * Description :
 * Created by vicwing
 * Created Time 2018/7/6
 */
 class MainPluginForBuildSrc implements Plugin<Project> {

    @Override
    void apply(Project project) {
//        project.task('vic_testPlugin') << {
//            println "Hello gradle plugin in src"
//        }

        project.extensions.create('pluginsrc', MyExtension)

        project.task('testPlugin') << {
            println project.pluginsrc.message
        }
    }
}
