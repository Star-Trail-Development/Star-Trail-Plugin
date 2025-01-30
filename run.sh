rm ./server/plugins/StarTrailPlugin-*.jar
cp ./build/libs/StarTrailPlugin-*.jar ./server/plugins/
cd server
java -jar arclight-forge-1.20.1-1.0.6.jar nogui
