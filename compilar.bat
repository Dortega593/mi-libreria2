@echo off

echo.
echo ::::::::::::::::::::::::::: C L O N A N D O  R E P O S I T O R I O ::::::::::::::::::::::::::
echo.
git clone https://github.com/Dortega593/mi-libreria2.git

echo.
echo ::::::::::::::::::::::::::::: C O M P I L A N D O   A P P - W E B :::::::::::::::::::::::::::::
cd .\app-web\
call .\gradlew clean
call .\gradlew build
call .\gradlew installDist
echo.
echo :::::::::::::::::::::: C O N S T R U Y E N D O   I M A G E N - D O C K E R ::::::::::::::::::::
echo.
docker build . -t jaimesalvador/app-web:1.0.1

echo.
echo :::::::::::::::::::::::::: C O M P I L A N D O   A P P - B O O K S  :::::::::::::::::::::::::::
echo.
cd ..
cd .\app-books\
call .\gradlew clean
call .\gradlew build
call .\gradlew installDist
echo.
echo :::::::::::::::::::::: C O N S T R U Y E N D O   I M A G E N   D O C K E R ::::::::::::::::::::
echo.
docker build . -t jaimesalvador/app-book:1.0.1

echo.
echo ::::::::::::::::::::::::: C O M P I L A N D O   A P P - A U T H O R S :::::::::::::::::::::::::
cd ..
cd .\app-author\
call .\gradlew clean
call .\gradlew build
call .\gradlew installDist
echo.
echo :::::::::::::::::::::: C O N S T R U Y E N D O   I M A G E N   D O C K E R ::::::::::::::::::::
echo.
docker build . -t jaimesalvador/app-author:1.0.1

echo.
echo :::::::::::::::::::: S U B I E N D O  I M A G E N E S  A  D O C K E R  H U B ::::::::::::::::::
echo.

echo.
echo :::::::::::::::::::::::::::::::: S U B I E N D O  A P P - W E B :::::::::::::::::::::::::::::::
echo.
docker push jaimesalvador/app-web:1.0.1

echo.
echo ::::::::::::::::::::::::::::: S U B I E N D O  A P P - A U T H O R ::::::::::::::::::::::::::::
echo.
docker push jaimesalvador/app-author:1.0.1

echo.
echo ::::::::::::::::::::::::::::::: S U B I E N D O  A P P - B O O K ::::::::::::::::::::::::::::::
echo.
docker push jaimesalvador/app-book:1.0.1

echo.
echo :::::::::::::::::::::::::::: D E S P L E G A N D O  P R O Y E C T O :::::::::::::::::::::::::::
echo.
cd ..
docker compose up