FROM openjdk:8
MAINTAINER ndrouin
ENV LANG fr_FR.UTF-8

#change sh to bash
RUN rm /bin/sh && ln -s /bin/bash /bin/sh

#go home
WORKDIR /root

#install conscript
RUN PATH=$PATH:~/bin
RUN export PATH
RUN CONSCRIPT_HOME=/root
RUN export CONSCRIPT_HOME
RUN source ~/.bashrc
RUN wget https://raw.githubusercontent.com/foundweekends/conscript/master/setup.sh
RUN chmod +x setup.sh
RUN ./setup.sh

#install giter8
RUN /root/.conscript/bin/cs foundweekends/giter8

#get skeleton project
RUN /root/.conscript/bin/g8 scalatra/scalatra-sbt --organization=easywebsites --package=com.easywebsites.app --name=EasyWebsitesApp --servlet_name=ControllerServlet --scala_version=2.12.1 --sbt_version=0.13.13 --version=1.0 --scalatra_version=2.5.0

#compile project
WORKDIR /root/easywebsitesapp
RUN chmod u+x /root/easywebsitesapp/sbt
RUN /root/easywebsitesapp/sbt -batch -sbt-create
RUN /root/easywebsitesapp/sbt -sbt-create jetty:start
ENTRYPOINT /root/easywebsitesapp/sbt -sbt-create jetty:start shell

#open firewall
EXPOSE 8080

