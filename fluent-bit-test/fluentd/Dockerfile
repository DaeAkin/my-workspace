FROM fluent/fluentd:v1.15-1

# Use root account to install dependencies
USER root

# Install FluentD plugins and dependencies
RUN apk add --no-cache --update \
        build-base \
        mariadb-dev \
        libffi-dev \
        ruby-dev \
        libc-dev \
        yaml-dev \
        zlib-dev \
        openssl-dev \
        linux-headers && \
    gem install mysql2 -v 0.5.3 && \
    gem install fluent-plugin-sql

# Return to default user
USER fluent
