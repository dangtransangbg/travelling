package travelling.api.app.repository.specification;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import travelling.api.app.entity.Blog;
import travelling.api.app.model.request.blog.BlogFilterRequest;

public class BlogSpecification {

    public static Specification<Blog> filterBlog(BlogFilterRequest blogFilterRequest) {
        return Specification.where(withUserName(blogFilterRequest.getUserName()))
                .and(withUrl(blogFilterRequest.getUrl()))
                .and(withStatus(blogFilterRequest.getStatus()));
    }

    public static Specification<Blog> filterUser(long userId) {
        return Specification.where(withUserId(userId));
    }

    private static Specification<Blog> withUserId(long userId) {
        if (userId == 0) return null;

        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.join("user").get("id"), userId);
    }

    private static Specification<Blog> withUserName(String userName) {
        if (StringUtils.isBlank(userName)) return null;

        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("createdBy"), userName);
    }

    private static Specification<Blog> withUrl(String url) {
        if (StringUtils.isBlank(url)) return null;

        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("url"), url);
    }

    private static Specification<Blog> withStatus(String status) {
        if (StringUtils.isBlank(status)) return null;

        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), status);
    }
}
