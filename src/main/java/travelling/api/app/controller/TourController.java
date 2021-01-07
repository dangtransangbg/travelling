package travelling.api.app.controller;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import travelling.api.app.common.role.ADMIN;
import travelling.api.app.controller.BaseController;
import travelling.api.app.model.request.tour.TourFilterRequest;
import travelling.api.app.model.request.tour.TourSaveRequest;
import travelling.api.app.model.request.tour.TourUpdateRequest;
import travelling.api.app.model.response.ObjectResponse;
import travelling.api.app.model.response.tour.TourDetailResponse;
import travelling.api.app.model.response.tour.TourResponse;
import travelling.api.app.service.TourService;

@RestController
@Data
public class TourController extends BaseController {

    private final TourService tourService;

    @PostMapping("/tour")
    @ADMIN
    public ResponseEntity<ObjectResponse> save(@RequestBody TourSaveRequest tourSaveRequest) {
        return success(tourSaveRequest, tourService::save);
    }


    @PatchMapping("/tour/{id}/hot")
    @ADMIN
    public ResponseEntity<ObjectResponse> updateHot(@RequestParam Boolean hot, @PathVariable long id) {
        TourUpdateRequest tourUpdateRequest = TourUpdateRequest
                .builder()
                .id(id)
                .hot(hot)
                .build();

        return success(tourUpdateRequest, tourService::updateTourToHot);
    }

    @PatchMapping("/tour/{id}/status")
    @ADMIN
    public ResponseEntity<ObjectResponse> updateStatus(@RequestParam String status, @PathVariable long id) {
        TourUpdateRequest tourUpdateRequest = TourUpdateRequest
                .builder()
                .id(id)
                .status(status)
                .build();

        return success(tourUpdateRequest, tourService::updateTourStatus);
    }
    @GetMapping("/tour")
    public ModelAndView tour(){
        ModelAndView mav = new ModelAndView("tour-home");
        return mav;
    }
    @GetMapping("/listTour")
    public ModelAndView tourList(){
        ModelAndView mav = new ModelAndView("tour-list");
        return mav;
    }
    @GetMapping("/tour/{id}")
    public ModelAndView getById(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("tour-detail");
        TourDetailResponse tour = tourService.findTourById(id);
        TourResponse tourResponse = tourService.findTourId(id);
        mav.addObject("tours",tour);
        mav.addObject("tour",tourResponse);
        return mav;
    }

//    @GetMapping("/tours")
//    @ADMIN
//    public ResponseEntity<ObjectResponse> getAll(@ModelAttribute TourFilterRequest tourFilterRequest) {
//        return success(tourFilterRequest, tourService::getAll);
//    }
}
