package dev.kostie.cicddemo.repository;

import dev.kostie.cicddemo.entity.Cat;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class CatRepositoryTest {
    @Autowired
    private CatRepository catRepository;

    @Test
    public void CatRepository_Save_ReturnSavedCat() {
        // Arrange something.
        Cat cat = Cat.builder()
                .name("Tabby")
                .color("Orange")
                .build();

        // Act on it.
        Cat savedCat = catRepository.save(cat);

        // Assert `Save` and `ReturnSavedCat` something.
        Assertions.assertThat(savedCat).isNotNull();
        Assertions.assertThat(savedCat.getId()).isGreaterThan(0);
    }
}