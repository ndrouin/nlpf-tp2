#/bin/sh
cp -rf /root/nlpf-tp2/src /root/easywebsitesapp/
cp -rf /root/nlpf-tp2/build.sbt /root/easywebsitesapp/build.sbt
./sbt package
cp -rf /root/easywebsitesapp/target/scala-2.12/easywebsitesapp_2.12-1.0.war /var/lib/jetty/webapps/root.war
jetty.sh restart
