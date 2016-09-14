Leviathan Studio Library
=========================

###How to install
####Universal for users :
* Download the universal version [here](https://files.leviathan-studio.com/build/ls-lib/)
* Place the file in the folder .minecraft/mods/

####Source for modder :

In the file build.gradle, add this :
```groovy
repositories {
    maven {
        name = "leviathan"
        url = "http://maven.leviathan-studio.com/artifactory/leviathan/"
    }
}

dependencies {
   compile 'com.leviathanstudio.lib:LS-Lib:${version}-mc${mcversion}:dev'
}
```
The list of available versions ​​is [here](http://maven.leviathan-studio.com/artifactory/leviathan/com/leviathanstudio/lib/LS-Lib/)