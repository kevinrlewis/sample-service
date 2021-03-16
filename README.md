# sample-service

Requires:
  schema registry
  kafka bootstrap server
  
Can use Confluents local environment set up.

Run with SPRING_PROFILES_ACTIVE=local

Local profile:

spring.kafka.bootstrap-servers=localhost:9092
spring.kafka.properties.schema.registry.url=http://localhost:8081

logging.level.root=TRACE
