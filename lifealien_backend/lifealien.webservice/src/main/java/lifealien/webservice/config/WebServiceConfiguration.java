package lifealien.webservice.config;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lifealien.webservice.server.DemoService;
import lifealien.webservice.server.DemoServiceImpl;

@Configuration
public class WebServiceConfiguration {
	@Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new CXFServlet(),"/demo/*");
    }
 
    @Bean(name = Bus.DEFAULT_BUS_ID)
    public SpringBus springBus() {
        return new SpringBus();
    }
 
    @Bean
    public DemoService demoService() {
        return new DemoServiceImpl();
    }
 
    @Bean
    public Endpoint endpoint(SpringBus springBus, DemoService demoService) {
        Endpoint endpoint = new EndpointImpl(springBus, demoService);
        endpoint.publish("/api");
        return endpoint;
    }
}
