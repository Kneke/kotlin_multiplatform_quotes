# .sh

# Build backend jar with gradle
../gradlew build

# Build docker image from dockerfile
docker build -t backend-all .

# Run docker image localy on port 3001
docker run -m512M --cpus 2 -it -p 3001:3001 --rm backend-all