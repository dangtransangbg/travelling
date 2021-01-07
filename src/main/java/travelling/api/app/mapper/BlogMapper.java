package travelling.api.app.mapper;

import org.springframework.stereotype.Component;
import travelling.api.app.common.constant.BlogStatus;
import travelling.api.app.entity.Blog;
import travelling.api.app.model.request.blog.BlogSaveRequest;
import travelling.api.app.model.response.blog.BlogDetailResponse;
import travelling.api.app.model.response.blog.BlogResponse;
import travelling.api.app.util.BeanUtils;

@Component
public class BlogMapper implements Mapper {

    public Blog convertToEntity(BlogSaveRequest saveRequest) {
        Blog blog = new Blog();
        BeanUtils.refine(saveRequest, blog, BeanUtils::copyNonNull);
        blog.setStatus(BlogStatus.PENDING);

        return blog;
    }

    public BlogDetailResponse convertToDetailResponse(Blog blog) {
        BlogDetailResponse blogDetailResponse = new BlogDetailResponse();
        BeanUtils.refine(blog, blogDetailResponse, BeanUtils::copyNonNull);

        return blogDetailResponse;
    }

    public BlogResponse convertResponse(Blog blog) {
        BlogResponse blogResponse = new BlogResponse();
        BeanUtils.refine(blog, blogResponse, BeanUtils::copyNonNull);

        return blogResponse;
    }


}
