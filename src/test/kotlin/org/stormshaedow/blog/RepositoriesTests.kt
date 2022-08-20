package org.stormshaedow.blog

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.repository.findByIdOrNull

@DataJpaTest
class RepositoriesTests @Autowired constructor(
    val entityManager: TestEntityManager,
    val userResository: UserResository,
    val articleRepository: ArticleRepository
) {
    @Test
    fun `when findByIdOrNull then return Article`() {
        val stormshaedow = User("Stormshaedow", "Storm", "Shaedow")
        entityManager.persist(stormshaedow)
        val article = Article("Spring Framework 5.0 goes GA", "Dear Spring community ...", "Lorem ipsum", stormshaedow)
        entityManager.persist(article)
        val found = articleRepository.findByIdOrNull(article.id!!)
        assertThat(found).isEqualTo(article)
    }

    @Test
    fun `when findByLogin then return User`() {
        val stormshaedow = User("Stormshaedow", "Storm", "Shaedow")
        entityManager.persist(stormshaedow)
        entityManager.flush()
        val user = userResository.findByLogin(stormshaedow.login)
        assertThat(user).isEqualTo(stormshaedow)
    }
}