package kyc.config;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

   private static final Charset UTF8 = Charset.forName("UTF-8");


   // Cấu hình UTF-8 cho các trang.
   @Override
   public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
      StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
      List<MediaType> mediaTypeList = new ArrayList<MediaType>();
//      mediaTypeList.add(new MediaType("text", "plain", UTF8));
      mediaTypeList.add(MediaType.APPLICATION_JSON_UTF8);
      stringConverter.setSupportedMediaTypes(mediaTypeList);
      converters.add(stringConverter);

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



}
