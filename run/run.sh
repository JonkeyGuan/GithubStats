#!/bin/bash

myDir=`dirname "$0"`

username=OpenshiftForGithub
password=123456abc
mapping=${myDir}/conf/CompanyMapping.properties

java -cp "${myDir}/lib/*" com.redhat.github.Stats $mapping $username $password

