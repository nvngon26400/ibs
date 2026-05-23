rem STSのインストールパス
set STS_PATH=C:\SBIIFA\STS\sts-4.18.0.RELEASE

set MVN_EXE=%STS_PATH%\apache-maven-3.9.1\bin\mvn
set JAVA_HOME=%STS_PATH%\JDK\jdk-17.0.6.10-hotspot

cd %~dp0\..

call %MVN_EXE% install:install-file -Dfile=.\lib\earth-0.0.42.jar -DgroupId=com.sbibits -DartifactId=earth -Dversion=ifa-0.0.42 -Dpackaging=jar -DgeneratePom=true
call %MVN_EXE% install:install-file -Dfile=.\lib\pcenter-cli-core-j8-1.1.1.jar       -DgroupId=pcenter-cli -DartifactId=pcenter-cli-core-j8 -Dversion=1.1.1 -Dpackaging=jar -DgeneratePom=true
call %MVN_EXE% install:install-file -Dfile=.\lib\pcenter-cli-heracross-ifa-1.1.2.jar -DgroupId=pcenter-cli -DartifactId=pcenter-cli-heracross -Dversion=ifa-1.1.2 -Dpackaging=jar -DgeneratePom=true
call %MVN_EXE% install:install-file -Dfile=.\lib\pcenter-cli-yanap-1.18.4.jar     -DgroupId=pcenter-cli -DartifactId=pcenter-cli-yanap -Dversion=ifa-1.18.4 -Dpackaging=jar -DgeneratePom=true

pause
