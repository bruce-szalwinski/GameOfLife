#!/bin/bash

mvn install
java -cp $GROOVY_HOME/embeddable/groovy-all-2.1.9.jar:target/gameoflife-1.0-SNAPSHOT.jar gameoflife.GameOfLife
