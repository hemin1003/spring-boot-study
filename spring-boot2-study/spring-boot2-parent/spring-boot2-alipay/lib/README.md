进入项目根目录下的lib目录，运行下面命令，把包安装到maven本地仓库：

mvn install:install-file -Dfile=alipay-sdk-1.0.jar -DgroupId=alipay-sdk -DartifactId=alipay-sdk -Dversion=1.0 -Dpackaging=jar