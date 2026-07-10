package dev.kostie.cicddemo.repository;

import dev.kostie.cicddemo.entity.Cat;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.util.List;
import java.util.Optional;

// AAA --> Arrange, Act, and Assert.
// Naming convention is: The Class we are testing for,
// the Method we are testing and the result we expect to see.

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class CatRepositoryTest {
    @Autowired
    private CatRepository catRepository;

    @Test
    public void CatRepository_Save_NotNullAndGreaterThanZero() {
        // Arrange
        Cat cat = Cat.builder()
                .name("Tabby")
                .color("Orange")
                .build();

        // Act
        Cat savedCat = catRepository.save(cat);

        // Assert
        Assertions.assertThat(savedCat).isNotNull();
        Assertions.assertThat(savedCat.getId()).isGreaterThan(0);
    }

    @Test
    public void CatRepository_FindAll_IsNotNullAndEqualToTwo() {
        // Arrange
        Cat catOne = Cat.builder()
                .name("Tom")
                .color("Grey")
                .build();

        Cat catTwo = Cat.builder()
                .name("Benny")
                .color("Black")
                .build();

        // Act
        catRepository.save(catOne);
        catRepository.save(catTwo);
        List<Cat> catList = catRepository.findAll();

        // Assert
        Assertions.assertThat(catList).isNotNull();
        Assertions.assertThat(catList.size()).isEqualTo(2);

    }


    @Test
    public void CatRepository_FindById_NotNull() {
        // Arrange
        Cat cat = Cat.builder()
                .name("Tom")
                .color("Grey")
                .build();

        // Act
        catRepository.save(cat);
        Optional<Cat> savedCat = catRepository.findById(cat.getId());

        // Assert
        Assertions.assertThat(savedCat).isNotNull();

    }

    @Test
    public void CatRepository_FindByColor_NotNull() {
        // Arrange
        Cat cat = Cat.builder()
                .name("Tom")
                .color("Black")
                .build();

        // Act
        catRepository.save(cat);
        Optional<Cat> savedCat = catRepository.findByColor(cat.getColor());

        // Assert
        Assertions.assertThat(savedCat).isNotNull();
    }

    @Test
    public void CatRepository_UpdateCat_NotNullAndEqualToBenny() {
        // Arrange
        Cat cat = Cat.builder()
                .name("Tabby")
                .color("Orange")
                .build();

        // Act
        catRepository.save(cat);
        Cat savedCat = catRepository.findById(cat.getId()).get();
        savedCat.setName("Benny");
        Cat updatedCat = catRepository.save(savedCat);

        // Assert
        Assertions.assertThat(updatedCat).isNotNull();
        Assertions.assertThat(updatedCat.getName()).isEqualTo("Benny");
    }

    @Test
    public void CatRepository_DeleteCat_IsEmpty() {
        // Arrange
        Cat cat = Cat.builder()
                .name("Tom")
                .color("Black")
                .build();

        // Act
        catRepository.save(cat);
        catRepository.deleteById(cat.getId());
        Optional<Cat> catReturn = catRepository.findById(cat.getId());

        // Assert
        Assertions.assertThat(catReturn).isEmpty();
    }
}