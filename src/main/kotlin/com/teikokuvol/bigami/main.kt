package com.teikokuvol.bigami

import io.vertx.core.AbstractVerticle
import io.vertx.core.Vertx
import io.vertx.ext.web.Router


object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        println("start up")
        val vertx = Vertx.vertx()
        vertx.deployVerticle(Preseitation())
    }
}

class Preseitation : AbstractVerticle() {
    var hp = 10
    override fun start() {
        var server = vertx.createHttpServer()

        var router = Router.router(vertx)



        router.route("/info").handler({ request ->
            // This handler gets called for each request that arrives on the server
            var response = request.response()
            response.putHeader("content-type", "text/plain")
            // Write to the response and end it
            response.end("Hello World! hp:$hp")
            damage()
        })
        server.requestHandler { router.accept(it) }
                .listen(8095)
    }

    fun damage() {
        hp--
    }

}