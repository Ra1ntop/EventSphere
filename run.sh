cd ApiGateway || exit
./gradlew clean build

cd ../EventService || exit
./gradlew clean build

cd ../NotificationService || exit
./gradlew clean build

cd ../UserService || exit
./gradlew clean build

docker-compose up --build