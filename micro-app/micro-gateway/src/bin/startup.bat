rem  windows启动脚本
rem
rem  author: laifeiyang
rem  date: 2021-07-18
rem ======================================================================

rem startup jar
java -jar ../lib/micro-gateway-1.0.0-SNAPSHOT.jar --spring.config.location=../config/ --microgateway.isEnableAnsi=false --logging.config=../config/logback.xml

pause