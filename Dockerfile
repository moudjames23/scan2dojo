FROM eclipse-temurin:22-alpine AS jre-builder

# Install binutils, required by jlink
RUN apk update &&  \
    apk add binutils

# Build small JRE image
RUN $JAVA_HOME/bin/jlink \
         --verbose \
         --add-modules ALL-MODULE-PATH \
         --strip-debug \
         --no-man-pages \
         --no-header-files \
         --compress=2 \
         --output /optimized-jdk-17

# Second stage, Use the custom JRE and build the app image
FROM alpine:latest
ENV JAVA_HOME=/opt/jdk/jdk-17
ENV PATH="${JAVA_HOME}/bin:${PATH}"

# copy JRE from the base image
COPY --from=jre-builder /optimized-jdk-17 $JAVA_HOME

# Add app user
ARG APPLICATION_USER=spring

# Create a user to run the application, don't run as root
RUN addgroup --system $APPLICATION_USER &&  adduser --system $APPLICATION_USER --ingroup $APPLICATION_USER

# Create the application directory
RUN mkdir /app && chown -R $APPLICATION_USER /app

COPY --chown=$APPLICATION_USER:$APPLICATION_USER target/*.jar /app/app.jar

WORKDIR /app

USER $APPLICATION_USER

ENTRYPOINT [ "java", "-jar", "/app/app.jar" ]