package org.stormshaedow.blog

import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BlogConfiguration {
    @Bean
    fun databaseInitializer(
        userResository: UserResository,
        articleRepository: ArticleRepository
    ) = ApplicationRunner {
        val stormshaedow = userResository.save(User("stormshaedow", "Storm", "Shaedow"))
        articleRepository.save(Article(
            title = "Reactor Bismuth is out",
            headline = "Lorem ipsum",
            content = "dolor sit amet",
            author = stormshaedow
        ))
        articleRepository.save(Article(
            title = "Reactor Aluminium has landed",
            headline = "Lorem ipsum",
            content = "dolor sit amet",
            author = stormshaedow
        ))
    }
}