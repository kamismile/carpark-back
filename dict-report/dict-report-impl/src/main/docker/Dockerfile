################################################
#             JAVA MICROSERVICE IMAGE          #
################################################
FROM fabric8/java-centos-openjdk8-jre

LABEL ${LABEL_GROUP}="${LABEL_ID}"
LABEL description="${LABEL_DESCRIPTION}"

ENV JAVA_APP_JAR ${JAR_NAME_TO_RUN}
ENV AB_OFF true

EXPOSE ${PORT_TO_EXPOSE}

ADD $JAVA_APP_JAR /deployments/