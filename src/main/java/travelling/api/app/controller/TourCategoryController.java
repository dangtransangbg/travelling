package travelling.api.app.controller;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import travelling.api.app.controller.BaseController;
import travelling.api.app.model.request.tourcategory.TourCategorySaveRequest;
import travelling.api.app.model.response.ObjectResponse;
import travelling.api.app.service.TourCategoryService;

@RestController
@Data
public class TourCategoryController extends BaseController {

    private final TourCategoryService tourCategoryService;


    @PostMapping("/tourCategory")
    public ResponseEntity<ObjectResponse> save(@RequestBody TourCategorySaveRequest tourCategorySaveRequest) {
        return success(tourCategorySaveRequest, tourCategoryService::save);
    }


}
