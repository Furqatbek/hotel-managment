FROM openjdk:17-alpine AS base
WORKDIR /app

FROM base AS build
RUN apk add --no-cache bash

COPY gradlew ./
COPY gradle gradle
COPY . .
RUN chmod +x ./gradlew

RUN /bin/bash -c "./gradlew build -x test --no-daemon";

FROM base
WORKDIR /app
COPY --from=build /app/build/libs/Mehmonxona-0.0.1-SNAPSHOT.jar ./

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "Mehmonxona-0.0.1-SNAPSHOT.jar"]