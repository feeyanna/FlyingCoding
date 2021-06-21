rem  windows启动脚本
rem
rem  author: laifeiyang
rem  date: 2019-11-19
rem ======================================================================

rem startup jar
java -jar ../lib/micro-business-A-api-1.0.0-SNAPSHOT.jar --spring.config.location=../config/ --microbusinessAapi.isEnableAnsi=false --logging.config=../config/logback.xml

pause