#!/bin/bash
git checkout master
./gradlew javadoc
rm -rf docs/
mkdir docs
cp -r build/docs/javadoc/* docs/
git add docs/
git commit -m "I generated the docs! -The Oven"
git push https://${GH_TOKEN}@github.com/BREAD5940/Pantry-Logging.git
