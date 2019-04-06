package org.mpatapenka.secp.web.rest.v1;

import lombok.RequiredArgsConstructor;
import org.mpatapenka.secp.domain.Size;
import org.mpatapenka.secp.service.SizeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("api/v1/sizes")
@RequiredArgsConstructor
public class SizeController {
    private final SizeService sizeService;

    @GetMapping
    public ResponseEntity<Collection<Size>> getSizes(@RequestParam(value = "archived", required = false) boolean archived) {
        return ResponseEntity.ok(archived ? sizeService.getAll() : sizeService.getAllActive());
    }

    @GetMapping(params = "category_id")
    public ResponseEntity<Collection<Size>> getSizes(@RequestParam("category_id") long categoryId) {
        return ResponseEntity.ok(sizeService.getAll(categoryId));
    }

    @PutMapping
    public void updateSizes(Collection<Size> sizes) {
        sizeService.saveAll(sizes);
    }

    @DeleteMapping("{id}")
    public void removeSize(@PathVariable("id") long sizeId) {
        sizeService.removeAll(Collections.singleton(sizeId));
    }
}