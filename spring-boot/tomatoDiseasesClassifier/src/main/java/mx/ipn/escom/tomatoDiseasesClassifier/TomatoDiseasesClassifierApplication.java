package mx.ipn.escom.tomatoDiseasesClassifier;

import java.util.EnumSet;
import javax.faces.webapp.FacesServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TomatoDiseasesClassifierApplication {

	public static void main(String[] args) {
		SpringApplication.run(TomatoDiseasesClassifierApplication.class, args);
	}
        
        @Bean
        public ServletRegistrationBean servletRegistrationBean() {
            FacesServlet servlet = new FacesServlet();
            return new ServletRegistrationBean(servlet, "*.jsf");
        }

        @Bean
        public FilterRegistrationBean rewriteFilter() {
            FilterRegistrationBean rwFilter = new FilterRegistrationBean(new RewriteFilter());
            rwFilter.setDispatcherTypes(EnumSet.of(DispatcherType.FORWARD, DispatcherType.REQUEST,
                    DispatcherType.ASYNC, DispatcherType.ERROR));
            rwFilter.addUrlPatterns("/*");
            return rwFilter;
        }
}
