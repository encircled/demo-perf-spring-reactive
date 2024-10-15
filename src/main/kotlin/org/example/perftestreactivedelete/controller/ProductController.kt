package org.example.perftestreactivedelete.controller

import org.example.perftestreactivedelete.service.ProductService
import org.example.perftestreactivedelete.model.Product
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class ProductController(
    val productService: ProductService
) {

    @GetMapping("/createData")
    fun createData(): Mono<String> {
        return productService.createTestData()
    }

    @GetMapping("/{name}")
    fun byName(@PathVariable("name") name: String): Mono<Product> {
        return productService.byName(name)
    }

    @GetMapping("/all")
    fun all(): Flux<Product> {
        return productService.all()
    }

}