package travelling.api.app.service;


import travelling.api.app.model.request.blog.BlogFilterRequest;
import travelling.api.app.model.request.blog.BlogSaveRequest;
import travelling.api.app.model.request.blog.BlogUpdateStatus;
import travelling.api.app.model.response.ListResponse;
import travelling.api.app.model.response.blog.BlogDetailResponse;
import travelling.api.app.model.response.blog.BlogResponse;

public interface BlogService {

    void save(BlogSaveRequest blogSaveRequest);

    void updateStatus(BlogUpdateStatus blogUpdateStatus);

    BlogDetailResponse getById(long id);

    ListResponse<BlogResponse> getAll(BlogFilterRequest blogFilterRequest);
}
