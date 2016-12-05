@echo off

set "myDir=%~dp0"


set "username=OpenshiftForGithub"
set "password=123456abc"
set "mapping=%myDir%conf\CompanyMapping.properties"

java -cp %myDir%lib\* com.redhat.github.Stats %mapping% %username% %password%

pause


