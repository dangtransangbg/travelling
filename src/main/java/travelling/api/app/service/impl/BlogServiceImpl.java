package travelling.api.app.service.impl;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import travelling.api.app.common.constant.MessageConstant;
import travelling.api.app.common.role.RoleConstant;
import travelling.api.app.entity.Blog;
import travelling.api.app.exception.AccessDeniedException;
import travelling.api.app.exception.ObjectNotFoundException;
import travelling.api.app.mapper.BlogMapper;
import travelling.api.app.model.request.blog.BlogFilterRequest;
import travelling.api.app.model.request.blog.BlogSaveRequest;
import travelling.api.app.model.request.blog.BlogUpdateStatus;
import travelling.api.app.model.response.ListResponse;
import travelling.api.app.model.response.blog.BlogDetailResponse;
import travelling.api.app.model.response.blog.BlogResponse;
import travelling.api.app.repository.BlogRepository;
import travelling.api.app.repository.specification.BlogSpecification;
import travelling.api.app.service.BlogService;
import travelling.api.app.service.UserService;
import travelling.api.security.jwt.model.JwtPayload;

import java.util.List;
import java.util.Optional;

@Service
@Data
public class BlogServiceImpl implements BlogService {

    private final BlogMapper blogMapper;
    private final UserService userService;
    private final BlogRepository blogRepository;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(BlogSaveRequest blogSaveRequest) {
        Blog blog = blogMapper.convertToEntity(blogSaveRequest);
        blogRepository.save(blog);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(BlogUpdateStatus blogUpdateStatus) {
        blogRepository.update(blogUpdateStatus.getId(), blogUpdateStatus.getStatus());
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public BlogDetailResponse getById(long id) {
        JwtPayload jwtPayload = userService.parseToken();
        if (jwtPayload.getRole().contains(RoleConstant.USER)) {
            throw new AccessDeniedException(MessageConstant.ACCESS_DENIED.value());
        }
        Optional<Blog> blog = blogRepository.findById(id);
        if (!blog.isPresent()) {
            throw new ObjectNotFoundException(MessageConstant.BLOG_NOT_FOUND.value());
        }
        BlogDetailResponse blogDetailResponse = blogMapper.convertToDetailResponse(blog.get());
        return blogDetailResponse;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public ListResponse<BlogResponse> getAll(BlogFilterRequest blogFilterRequest) {
        JwtPayload jwtPayload = userService.parseToken();
        if (jwtPayload.getRole().contains(RoleConstant.USER)) {
            blogFilterRequest.setUserName(jwtPayload.getUserName());
        }
        Page<Blog> blogs = blogRepository.findAll(BlogSpecification.filterBlog(blogFilterRequest), blogFilterRequest.getPageRequest());
        List<BlogResponse> blogResponses = blogMapper.convertToList(blogMapper::convertResponse, blogs.getContent());
        long count = blogs.getTotalElements();
        return ListResponse.of(count, blogResponses);
    }


}
