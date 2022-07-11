ARG BUILD_HOME=/gradle-docker-example

FROM gradle:jdk11 as build-image

ARG BUILD_HOME
ENV APP_HOME=$BUILD_HOME
WORKDIR $APP_HOME

COPY --chown=gradle:gradle build.gradle settings.gradle $APP_HOME/
COPY --chown=gradle:gradle src $APP_HOME/src
#COPY --chown=gradle:gradle config $APP_HOME/config

RUN gradle --no-daemon build

#
# Java image for the application to run in.
#
FROM openjdk:12-alpine

ARG BUILD_HOME
ENV APP_HOME=$BUILD_HOME
COPY --from=build-image $APP_HOME/build/libs/app-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT java -jar app.jar