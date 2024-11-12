# --- Build vaihe ---
    FROM maven:3.8.6-eclipse-temurin-17-focal AS build

    # Määritetään työskentelyhakemisto
    WORKDIR /home/app
    
    # Kopioidaan pelkästään pom.xml riippuvuuksien lataamiseksi
    COPY pom.xml .
    
    # Ladataan riippuvuudet (vähentää myöhempien muutosten vaikutusta)
    RUN mvn dependency:go-offline -B
    
    # Kopioidaan lähdekoodit
    COPY src ./src
    
    # Rakennetaan projekti
    RUN mvn clean package -DskipTests
    
    # --- Runtime vaihe ---
    FROM eclipse-temurin:17-jre-focal
    
    # Määritetään työskentelyhakemisto
    WORKDIR /app
    
    # Kopioidaan build-vaiheessa rakennettu .jar tiedosto
    COPY --from=build /home/app/target/*.jar /app/elokuvaprojekti_sara.jar
    
    # Exposataan portti 8080
    EXPOSE 8080
    
    # Ajokomento
    ENTRYPOINT ["java", "-jar", "/app/elokuvaprojekti_sara.jar"]
    