package horizon.common.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class RestDispatcherServlet extends DispatcherServlet{
	public RestDispatcherServlet() {
		configure();
	}
	public RestDispatcherServlet(WebApplicationContext webApplicationContext) {
		super(webApplicationContext);
		configure();
	}
	
	private void configure() {
		setContextClass(AnnotationConfigWebApplicationContext.class);
		setContextConfigLocation(RestMvcConfiguration.class.getName());
	 }
}
