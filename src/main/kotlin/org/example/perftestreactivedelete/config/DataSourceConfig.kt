package org.example.perftestreactivedelete.config

import cz.encircled.joiner.kotlin.reactive.KtReactiveJoiner
import cz.encircled.joiner.reactive.ReactorJoiner
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DataSourceConfig {

    @Bean
    fun entityManagerFactory(
        @Value("\${SPRING_DATASOURCE_URL:jdbc:postgresql://localhost:5432/demo}") url: String
    ): EntityManagerFactory {
        return Persistence.createEntityManagerFactory(
            "reactiveTest",
            mutableMapOf("jakarta.persistence.jdbc.url" to url)
        )
    }

    @Bean
    fun joiner(emf: EntityManagerFactory) = ReactorJoiner(emf)

    @Bean
    fun joinerKt(emf: EntityManagerFactory) = KtReactiveJoiner(emf)

}