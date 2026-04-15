# Utilisez une image de base contenant Java 15 (AdoptOpenJDK 15)
FROM adoptopenjdk:15-jre-hotspot

# Copiez le fichier JAR de l'application Spring Boot dans l'image

COPY administration-presentation/target/administration-presentation-1.0.0.jar administration-presentation-1.0.0.jar
# Exposez le port 8063 sur le conteneur Docker
EXPOSE 8063

# Commande pour démarrer l'application Spring Boot lorsque le conteneur démarre
CMD ["java", "-jar","administration-presentation-1.0.0.jar"]
#aa