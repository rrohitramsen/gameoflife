#!/usr/bin/env bash
echo "*********************************************************************************************"
echo "*********************************   GAME OF LIFE      ***************************************"
echo "*********************************************************************************************"
echo "/////////////////////////////////////////////////////////////////////////////////////////////"

## Making an executable jar file

echo "*********************************************************************************************"
echo "**************************    MAKING THE FINAL JAR FILE     *********************************"
echo "*********************************************************************************************"
echo "mvn package"
mvn package

STATUS=$?
if [ $STATUS -eq 0 ]; then
echo "*********************************************************************************************"
echo "*******************************   Build Successful     **************************************"
echo "*********************************************************************************************"
else
echo "*********************************************************************************************"
echo "*********************************    Build Fail     *****************************************"
echo "*********************************************************************************************"
fi

echo "Please input seed file name (with path or on current dir) or to start with the given seeds PRESS ENTER."
echo "1. Blinker Seed"
echo "2. Beacon  Seed"
echo "3. Glider Seed"
echo "4. Toad Seed"
echo "5. Tub Seed"
read seed_file_name

if [ -z "$seed_file_name" ]; then

    file_path="src/test/resources/"
    seed_files=("blinker_seed.txt" "beacon_seed.txt" "glider_seed.txt" "toad_seed.txt" "tub_seed.txt")
    j=1
    for i in "${seed_files[@]}"
    do
        echo "*********************************************************************************************"
        echo "**********************************   $j. "$i"    ************************************"
        echo "*********************************************************************************************"
        java -jar ./target/game_of_life-1.0-SNAPSHOT.jar   $file_path$i
        ((j++))
     done

else
    echo "*********************************************************************************************"
    echo "***********************************   1.  $seed_file_name   *********************************"
    echo "*********************************************************************************************"
    java -jar ./target/game_of_life-1.0-SNAPSHOT.jar  $seed_file_name
fi

echo "*********************************************************************************************"
echo "**************************    GAME OF LIFE - THE END     ************************************"
echo "*********************************************************************************************"





