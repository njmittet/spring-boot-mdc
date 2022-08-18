package no.njm

import org.springframework.stereotype.Service
import kotlin.random.Random.Default.nextInt

@Service
class MessageService {

    private val log = getLogger()

    private val names = listOf("Jack", "Back", "Sack")

    fun selectRandomName(): String {
        val int = nextInt(from = 0, until = 2)
        val name = names[int]
        log.info("Randomly selected name: $name, having index: $int")
        return name
    }
}
