package travelling.api.app.filter;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class CustomSiteMeshFilter extends ConfigurableSiteMeshFilter {
    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/*","/decorators/web.jsp")
                //.addDecoratorPath("/error","/decorators/web.jsp")
                .addDecoratorPath("/login","/decorators/login.jsp")
                .addDecoratorPath("/admin*","/decorators/admin.jsp")
                .addExcludedPath("/api**").addExcludedPath("/ckfinder**");
    }

}
