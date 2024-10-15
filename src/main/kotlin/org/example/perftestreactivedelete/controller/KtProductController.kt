package org.example.perftestreactivedelete.controller

import cz.encircled.joiner.kotlin.JoinerKtOps.eq
import cz.encircled.joiner.kotlin.JoinerKtQueryBuilder.all
import cz.encircled.joiner.kotlin.reactive.KtReactiveJoiner
import org.example.perftestreactivedelete.model.Product
import org.example.perftestreactivedelete.model.QProduct.product
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class KtProductController(
    val joiner: KtReactiveJoiner
) {

    @GetMapping("/kt/{name}")
    suspend fun byName(@PathVariable("name") name: String): List<Product> {
        return joiner.find(product.all() where { it.name eq name })
    }

}