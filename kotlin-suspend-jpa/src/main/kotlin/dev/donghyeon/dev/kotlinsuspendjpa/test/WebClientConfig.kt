package dev.donghyeon.dev.kotlinsuspendjpa.test

import io.netty.channel.ChannelOption
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import java.time.Duration
@Configuration
class WebClientConfig {

    @Bean
    fun webClient(webClientBuilder: WebClient.Builder): WebClient = webClientBuilder
        .clientConnector(createReactorClientHttpConnector(5))
        .codecs { configurer -> // 코덱 사이즈를 default 256k에서 1M으로 변경
            configurer.defaultCodecs().maxInMemorySize(1 * 1024 * 1024)
        }
        .build()

    private fun createReactorClientHttpConnector(sec: Int): ReactorClientHttpConnector = ReactorClientHttpConnector(
        HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, sec * 1000)
            .responseTimeout(Duration.ofSeconds(180))
            .compress(true)
            .keepAlive(true)
    )
}
