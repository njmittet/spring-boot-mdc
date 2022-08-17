package no.njm

import org.slf4j.MDC
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

data class Greeting(val id: Long, val content: String)

@RestController
class GreetingController(private val messageService: MessageService) {

    private val log = getLogger()
    private val counter = AtomicLong()

    // Expression body function.
    @GetMapping("/hello")
    fun hello(
        @RequestHeader headers: Map<String, String>,
    ): Greeting {
        headers["x-request-id"]?.also { MDC.put("x-request-id", it) }
        val name = messageService.selectRandomName()
        log.info("Hello, $name")
        MDC.clear()
        return Greeting(counter.incrementAndGet(), "Hello, $name")
    }
}
