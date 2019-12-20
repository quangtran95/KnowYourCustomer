package kyc.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

   private static final Charset UTF8 = Charset.forName("UTF-8");


   // Cấu hình UTF-8 cho các trang.
   @Override
   public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
      List<MediaType> mediaTypeList = new ArrayList<MediaType>();
      mediaTypeList.add(MediaType.TEXT_PLAIN);
      mediaTypeList.add(MediaType.APPLICATION_JSON_UTF8);
      mediaTypeList.add(MediaType.APPLICATION_JSON);
      mediaTypeList.add(MediaType.APPLICATION_XML);
      mediaTypeList.add(MediaType.APPLICATION_JSON_UTF8);

      StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
      stringConverter.setSupportedMediaTypes(mediaTypeList);
      converters.add(stringConverter);

      ResourceHttpMessageConverter resourceConverter = new ResourceHttpMessageConverter();
      resourceConverter.setSupportedMediaTypes(mediaTypeList);
      converters.add(resourceConverter);

      MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
      jsonConverter.setSupportedMediaTypes(mediaTypeList);
      converters.add(jsonConverter);

      // Add other converters ...
   }



   // Cấu hình để sử dụng các file nguồn tĩnh (html, image, ..)
   // Tương đương với <mvc:resources/> trong cấu hình sử dụng XML.
   @Override
   public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler("/css/**").addResourceLocations("/css/").setCachePeriod(31556926);
      registry.addResourceHandler("/img/**").addResourceLocations("/img/").setCachePeriod(31556926);
      registry.addResourceHandler("/js/**").addResourceLocations("/js/").setCachePeriod(31556926);
      registry.addResourceHandler("/pages/**").addResourceLocations("/pages/").setCachePeriod(31556926);
   }

   // equivalent for <mvc:default-servlet-handler/> tag
   @Override
   public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
      configurer.enable();
   }

   @Override
   public void addViewControllers(ViewControllerRegistry registry) {
      registry.addViewController("../").setViewName("index");
   }
}
