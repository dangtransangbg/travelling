package travelling.api.app.controller;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import travelling.api.app.common.constant.BlogStatus;
import travelling.api.app.common.role.ADMIN;
import travelling.api.app.common.role.USER;
import travelling.api.app.model.request.blog.BlogFilterRequest;
import travelling.api.app.model.request.blog.BlogSaveRequest;
import travelling.api.app.model.request.blog.BlogUpdateStatus;
import travelling.api.app.model.response.ObjectResponse;
import travelling.api.app.service.BlogService;

@RestController
@Data
public class BlogController extends BaseController {

    private BlogService blogService;


    @PostMapping("/blog")
    @USER
    @ADMIN
    public ResponseEntity<ObjectResponse> save(@RequestBody BlogSaveRequest blogSaveRequest) {
        return success(blogSaveRequest, blogService::save);
    }

    @PatchMapping("/blog/{id}/approve")
    @ADMIN
    public ResponseEntity<ObjectResponse> approve(@PathVariable long id) {
        BlogUpdateStatus blogUpdateStatus = BlogUpdateStatus
                .builder()
                .id(id)
                .status(BlogStatus.APPROVE)
                .build();
        return success(blogUpdateStatus, blogService::updateStatus);
    }

    @PatchMapping("/blog/{id}/publish")
    @ADMIN
    public ResponseEntity<ObjectResponse> publish(@PathVariable long id) {
        BlogUpdateStatus blogUpdateStatus = BlogUpdateStatus
                .builder()
                .status(BlogStatus.PUBLISH)
                .id(id)
                .build();
        return success(blogUpdateStatus, blogService::updateStatus);
    }

    @GetMapping("/blog/{id}")
    @ADMIN
    public ResponseEntity<ObjectResponse> findById(@PathVariable long id) {
        return success(id, blogService::getById);
    }

    @GetMapping("/blogs")
    @ADMIN
    public ResponseEntity<ObjectResponse> gets(@ModelAttribute BlogFilterRequest blogFilterRequest) {
        return success(blogFilterRequest, blogService::getAll);
    }
}
