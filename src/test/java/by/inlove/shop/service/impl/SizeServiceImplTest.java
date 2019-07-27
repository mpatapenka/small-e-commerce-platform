package by.inlove.shop.service.impl;

import by.inlove.shop.domain.Size;
import by.inlove.shop.service.SizeService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class SizeServiceImplTest {
    @Autowired
    private SizeService sizeService;

    @Test
    void wholeFlow() {
        // Given
        Collection<Size> originalSizes = Lists.newArrayList(
                new Size(null, 100, "D", false),
                new Size(null, 75, "A", false),
                new Size(null, 80, "D", false),
                new Size(null, 100, "DD", false),
                new Size(null, 75, "B", false)
        );

        // When
        sizeService.saveAll(originalSizes);

        // Then
        assertThat(originalSizes).allSatisfy(size -> assertThat(size.getId()).isNotNull());
    }
}