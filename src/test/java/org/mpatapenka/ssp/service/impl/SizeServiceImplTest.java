package org.mpatapenka.ssp.service.impl;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mpatapenka.ssp.domain.Size;
import org.mpatapenka.ssp.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SizeServiceImplTest {
    @Autowired
    private SizeService sizeService;

    @Test
    public void wholeFlow() {
        Collection<Size> originalSizes = Lists.newArrayList(
                new Size(null, 100, "D", false),
                new Size(null, 75, "A", false),
                new Size(null, 80, "D", false),
                new Size(null, 100, "DD", false),
                new Size(null, 75, "B", false)
        );

        sizeService.saveAll(originalSizes);

        originalSizes.forEach(size -> assertThat(size.getId()).isNotNull());
    }
}