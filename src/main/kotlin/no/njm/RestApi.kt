package no.njm

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

data class Greeting(val id: Long, val content: String)

@RestController
class GreetingController(private val messageService: MessageService) {

    private val log = getLogger()
    private val counter = AtomicLong()

    @GetMapping("/hello")
    fun hello(): Greeting {
        val name = messageService.selectRandomName()
        log.info("Hello, $name")
        return Greeting(counter.incrementAndGet(), "Hello, $name")
    }
}
