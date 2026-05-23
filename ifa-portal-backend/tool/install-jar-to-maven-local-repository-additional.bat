rem JasperReportの古い依存URLを現在の依存URLに置き換える、Mavenのsetting.xmlを生成
echo 
mkdir "%USERPROFILE%\.m2" 2>nul

(
echo ^<settings
echo 	xmlns="http://maven.apache.org/SETTINGS/1.0.0"
echo 	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
echo 	xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 https://maven.apache.org/xsd/settings-1.0.0.xsd"
echo ^>
echo 	^<mirrors^>
echo 		^<mirror^>
echo 			^<id^>Jasper Third Party^</id^>
echo 			^<mirrorOf^>jaspersoft-third-party^</mirrorOf^>
echo 			^<url^>https://jaspersoft.jfrog.io/jaspersoft/third-party-ce-artifacts/^</url^>
echo 		^</mirror^>
echo 	^</mirrors^>
echo ^</settings^>
) > "%USERPROFILE%\.m2\settings.xml"

rem STSのインストールパス
set STS_PATH=C:\SBIIFA\STS\sts-4.18.0.RELEASE

set MVN_EXE=%STS_PATH%\apache-maven-3.9.1\bin\mvn
set JAVA_HOME=%STS_PATH%\JDK\jdk-17.0.6.10-hotspot

cd %~dp0\..

call %MVN_EXE% install:install-file -Dfile=.\lib\pcenter-cli-yanap-1.18.4.jar     -DgroupId=pcenter-cli -DartifactId=pcenter-cli-yanap -Dversion=1.18.4 -Dpackaging=jar -DgeneratePom=true

pause
