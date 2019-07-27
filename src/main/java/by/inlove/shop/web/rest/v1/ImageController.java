package by.inlove.shop.web.rest.v1;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import by.inlove.shop.domain.Image;
import by.inlove.shop.service.ImageService;
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
import java.util.Collection;

@RestController
@RequestMapping("api/v1/images")
@Slf4j
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<Collection<Image>> upload(@RequestParam("images") MultipartFile[] images) {
        return ResponseEntity.ok(imageService.saveAll(images));
    }

    @GetMapping("/{id:.+}")
    public ResponseEntity<Resource> download(@PathVariable long id, HttpServletRequest request) {
        Image image = imageService.get(id);

        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(image.getContent().getFile().getAbsolutePath());
        } catch (IOException e) {
            log.info("Could not determine file type of content: {}", image.getContent().getFilename());
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getName() + "\"")
                .body(image.getContent());
    }
}