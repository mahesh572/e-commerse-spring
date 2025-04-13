# Use a lightweight base image
FROM ubuntu:22.04

# Install system dependencies
RUN apt-get update && apt-get install -y \
    curl \
    git \
    gnupg \
    unzip \
    && rm -rf /var/lib/apt/lists/*

# Install Node.js (LTS version)
RUN curl -fsSL https://deb.nodesource.com/setup_lts.x | bash - \
    && apt-get install -y nodejs \
    && npm install -g yarn

# Install Java (OpenJDK 17)
RUN apt-get update && apt-get install -y openjdk-17-jdk

# Install Maven
ARG MAVEN_VERSION=3.9.6
RUN curl -O https://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
    && tar -xzf apache-maven-${MAVEN_VERSION}-bin.tar.gz -C /opt \
    && ln -s /opt/apache-maven-${MAVEN_VERSION}/bin/mvn /usr/bin/mvn \
    && rm apache-maven-${MAVEN_VERSION}-bin.tar.gz

# Install Docker CLI (not the daemon)
RUN curl -fsSL https://get.docker.com | sh

# Install Docker Compose (v2)
RUN mkdir -p ~/.docker/cli-plugins \
    && curl -SL https://github.com/docker/compose/releases/latest/download/docker-compose-linux-x86_64 -o ~/.docker/cli-plugins/docker-compose \
    && chmod +x ~/.docker/cli-plugins/docker-compose

# Verify installations
RUN java -version \
    && mvn --version \
    && node --version \
    && npm --version \
    && docker --version \
    && docker compose version

# Set working directory
WORKDIR /workspace

# Entrypoint
CMD ["/bin/bash"]