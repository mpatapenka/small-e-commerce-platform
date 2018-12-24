package org.mpatapenka.ssp.web.rest.v1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mpatapenka.ssp.domain.Image;
import org.mpatapenka.ssp.entity.ImageEntity;
import org.mpatapenka.ssp.service.ImageService;
import org.mpatapenka.ssp.transform.Transformer;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/images")
@Slf4j
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;
    private final Transformer<ImageEntity, Image> imageTransformer;

    @PostMapping
    public ResponseEntity<List<Image>> upload(@RequestParam("images") MultipartFile[] images) {
        return ResponseEntity.ok(imageService.saveAll(images).parallelStream()
                .map(imageTransformer::forward)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id:.+}")
    public ResponseEntity<Resource> download(@PathVariable long id, HttpServletRequest request) {
        Resource resource = imageService.getAsResource(id);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            log.info("Could not determine file type of resource: {}", resource.getFilename());
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}