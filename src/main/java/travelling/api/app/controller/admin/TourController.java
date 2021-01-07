package travelling.api.app.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import travelling.api.app.model.response.tour.TourDetailResponse;
import travelling.api.app.model.response.tour.TourResponse;
import travelling.api.app.service.TourService;

@Controller("adminTour")
@RequestMapping("/admin")
public class TourController {

    private TourService tourService;

    @Autowired
    public TourController(TourService tourService) {
        this.tourService = tourService;
    }


    @GetMapping("/tour-list")
    public String tourPage(){
        return "admin/tour/list";
    }

    @GetMapping("/tour-create")
    public String create(){
        return "admin/tour/edit";
    }
}
