package kyc.service;

import kyc.dao.CustomerInfoDao;
import kyc.domain.CustomerInfo;
import kyc.dto.CustomerInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("customerInfoService")
public class CustomerInfoServiceImp extends GenericService implements CustomerInfoService {

   @Autowired
   @Qualifier("customerInfoJpaDao")
   CustomerInfoDao customerInfoDao;

   public List<CustomerInfoDto> getListCustomerInfo() {
      List<CustomerInfo> customerInfoList = customerInfoDao.getListCustomerInfo();
      List<CustomerInfoDto> customerInfoDtoList = mapper.mapCollection(customerInfoList, CustomerInfoDto.class);
      return customerInfoDtoList;
   }

   @Override
   public void insertCustomerInfo(CustomerInfoDto customerInfoDto) {
      CustomerInfo customerInfo = mapper.map(customerInfoDto, CustomerInfo.class);
      customerInfoDao.insert(customerInfo);
   }

   @Override
   public void updateCustomerInfo(CustomerInfoDto customerInfoDto) {
      CustomerInfo customerInfo = customerInfoDao.findById(customerInfoDto.getId());
      if(customerInfo != null) {
         customerInfo.setFirstName(customerInfoDto.getFirstName());
         customerInfo.setLastName(customerInfoDto.getLastName());
         customerInfo.setEmail(customerInfoDto.getEmail());
         customerInfo.setIdNumber(customerInfoDto.getIdNumber());
         customerInfo.setTelephoneNumber(customerInfoDto.getTelephoneNumber());
         customerInfo.setAddress(customerInfoDto.getAddress());

         customerInfoDao.update(customerInfo);
      }
   }

   @Override
   public void deleteCustomerInfo(Long customerId) {
      CustomerInfo customerInfo = customerInfoDao.findById(customerId);
      if(customerInfo != null) {
         customerInfoDao.delete(customerId);
      }
   }
}
