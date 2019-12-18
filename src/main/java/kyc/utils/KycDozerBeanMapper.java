package kyc.utils;

import org.dozer.DozerBeanMapper;
import org.dozer.MappingException;

import java.util.ArrayList;
import java.util.List;

public class KycDozerBeanMapper extends DozerBeanMapper {

   public KycDozerBeanMapper() {
   }

   @SuppressWarnings("unchecked")
   public <T> List<T> mapCollection(Object source, Class<T> destinationClass, String mapId) throws MappingException {
      List<T> list = new ArrayList<T>();
      if(source instanceof List) {
         List<Object> objects = (List<Object>)source;
         for(Object object : objects) {
            T t = getMappingProcessor().map(object, destinationClass, mapId);
            list.add(t);
         }
      }
      return list;
   }

   @SuppressWarnings("unchecked")
   public <T> List<T> mapCollection(Object source, Class<T> destinationClass) throws MappingException {
      List<T> list = new ArrayList<T>();
      if(source instanceof List) {
         List<Object> objects = (List<Object>)source;
         for(Object object : objects) {
            T t = getMappingProcessor().map(object, destinationClass);
            list.add(t);
         }
      }
      return list;
   }
}
