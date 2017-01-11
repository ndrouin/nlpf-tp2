#!/bin/sh
mongod --dbpath /srv/mongodb&
jetty.sh start && tail -f /dev/null
