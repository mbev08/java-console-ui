FROM maven:3.9.9-eclipse-temurin-24 AS build

WORKDIR /build

# Copy pom.xml & download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source files and compile them
COPY . .
ENV MAVEN_OPTS="--enable-native-access=ALL-UNNAMED --sun-misc-unsafe-memory-access=allow"
RUN mvn -e package

# Explode fat runnable JARS
ARG DEPENDENCY=/build/target/dependency
RUN mkdir -p ${DEPENDENCY} && (cd ${DEPENDENCY}; jar -xf ../*.jar)
RUN mkdir -p ${DEPENDENCY}/lib && mvn dependency:copy-dependencies -DoutputDirectory=${DEPENDENCY}/lib


# Runnable image
FROM openjdk:24 AS runnable
VOLUME /tmp
VOLUME /logs
ARG DEPENDENCY=/build/target/dependency

# Create User&Group to not run docker images with root user
# RUN groupadd -S awesome && adduser -S awesome -G awesome
# USER awesome

# Set TERM environment variable for ANSI support
ENV TERM=xterm-256color

# Copy libraries & meta-info & classes
COPY --from=build ${DEPENDENCY}/ /app/
COPY --from=build ${DEPENDENCY}/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF

# Run application
ENTRYPOINT ["java", "--enable-native-access=ALL-UNNAMED", "--sun-misc-unsafe-memory-access=allow", "-cp", "app:app/lib/*", "com.mbev08.consoleui.Main"]

