package org.example.perftestreactivedelete.service

import cz.encircled.joiner.kotlin.JoinerKtOps.eq
import cz.encircled.joiner.kotlin.JoinerKtQueryBuilder.all
import cz.encircled.joiner.reactive.ReactorJoiner
import org.example.perftestreactivedelete.model.Product
import org.example.perftestreactivedelete.model.QProduct.product
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ProductService {
    fun createTestData(): Mono<String>
    fun byName(name: String): Mono<Product>
    fun all(): Flux<Product>
}

@Service
class ProductServiceImpl(
    val joiner: ReactorJoiner
) : ProductService {

    override fun createTestData(): Mono<String> {
        return Flux.range(1, 500000)
            .buffer(50000)
            .flatMapSequential {
                joiner.transaction {
                    persist(it.map { i ->
                        Product(i.toLong(), "Test$i")
                    })
                }
            }.then(Mono.just("Test data created"))
    }

    override fun byName(name: String): Mono<Product> {
        return joiner.findOneOptional(product.all() where { it.name eq name })
    }

    override fun all(): Flux<Product> {
        return joiner.find(product.all() limit 100)
    }

}