#!/usr/bin/env bash

export CATALINA_HOME=~/Desktop/ff/REST-API-PROJECT/


start_webBrowser() {
   open http://localhost:8080/crud/v1/task/getTasks
}

fail() {
  echo "There were errors"
}

end() {
  echo "Work is finished"
}

if ./runcrud.sh; then
   start_webBrowser

else
   fail
fi