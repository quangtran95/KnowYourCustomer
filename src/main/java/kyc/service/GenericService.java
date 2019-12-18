package kyc.service;

import kyc.utils.KycDozerBeanMapper;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class GenericService {
   @Autowired
   public KycDozerBeanMapper mapper;

   public static final ObjectMapper objectMapper;
   static {
      objectMapper = new ObjectMapper();
      objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
   }
}
