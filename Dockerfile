FROM openjdk:8-jdk

# Set the working directory
WORKDIR /app

# Copy necessary files
COPY . /app/

# Install dependencies
RUN apt-get update && apt-get install -y lsof default-jdk ant make python3-pip

# Install Flask and Zeep
RUN pip install Flask zeep

# Source the environment script
RUN echo "source /app/set-env-docker.sh" >> /etc/bash.bashrc

# Set environment variables
ENV DOCKER=true
ENV FILTERING_SERVER_HOST=filtering
ENV BOOKING_SERVER_HOST=booking
ENV WEB_SERVER_HOST=web
ENV FILTERING_SERVER_PORT=8088
ENV BOOKING_SERVER_PORT=8182
ENV WEB_SERVER_PORT=8081
ENV AXIS2_HOME=/app/axis2-1.8.2

# Expose necessary ports
EXPOSE 8081
EXPOSE 8088
EXPOSE 8182

# Start servers
CMD ["make"]
