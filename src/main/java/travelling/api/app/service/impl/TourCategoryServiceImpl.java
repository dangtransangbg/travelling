package travelling.api.app.service.impl;

import lombok.Data;
import org.springframework.stereotype.Service;
import travelling.api.app.entity.TourCategory;
import travelling.api.app.mapper.TourCategoryMapper;
import travelling.api.app.model.request.tourcategory.TourCategorySaveRequest;
import travelling.api.app.repository.TourCategoryRepository;
import travelling.api.app.service.TourCategoryService;

@Service
@Data
public class TourCategoryServiceImpl implements TourCategoryService {

    private final TourCategoryMapper tourCategoryMapper;
    private final TourCategoryRepository tourCategoryRepository;


    @Override
    public void save(TourCategorySaveRequest tourCategorySaveRequest) {
        TourCategory tourCategory = tourCategoryMapper.convertToEntity(tourCategorySaveRequest);
        tourCategoryRepository.save(tourCategory);
    }
}
