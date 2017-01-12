#!/bin/sh
mongod --dbpath /srv/mongodb&
mongo /root/init.js 
jetty.sh start && tail -f /dev/null
