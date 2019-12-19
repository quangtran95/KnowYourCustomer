package kyc.service;

import kyc.dto.CustomerInfoDto;

import java.util.List;

public interface CustomerInfoService {
   List<CustomerInfoDto> getListCustomerInfo(String searchContent);
   void insertCustomerInfo(CustomerInfoDto customerInfoDto);
   void updateCustomerInfo(CustomerInfoDto customerInfoDto);
   void deleteCustomerInfo(Long customerId);
}
