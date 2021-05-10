FROM haproxy:1.9.6

COPY haproxy.cfg /usr/local/etc/haproxy/haproxy.cfg
COPY gamepost.pem /etc/ssl/gamepost.pem
RUN mkdir /run/haproxy/