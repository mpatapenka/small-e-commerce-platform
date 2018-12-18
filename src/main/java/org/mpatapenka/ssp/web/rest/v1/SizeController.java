package org.mpatapenka.ssp.web.rest.v1;

import lombok.RequiredArgsConstructor;
import org.mpatapenka.ssp.domain.Size;
import org.mpatapenka.ssp.service.SizeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("api/v1/sizes")
@RequiredArgsConstructor
public class SizeController {
    private final SizeService sizeService;

    @GetMapping
    public ResponseEntity<Collection<Size>> getSizes() {
        return ResponseEntity.ok(sizeService.getAll());
    }

    @GetMapping(params = "category_id")
    public ResponseEntity<Collection<Size>> getSizes(@RequestParam("category_id") long categoryId) {
        return ResponseEntity.ok(sizeService.getAll(categoryId));
    }

    @PutMapping("{id}")
    public ResponseEntity<?> archiveSize(@PathVariable("id") long sizeId) {
        sizeService.archive(sizeId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> removeSize(@PathVariable("id") long sizeId) {
        sizeService.remove(sizeId);
        return ResponseEntity.ok().build();
    }
}