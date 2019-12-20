package kyc.service;

import kyc.dto.CustomerInfoDto;
import kyc.dto.SearchCustomerInfoDto;

import java.util.List;

public interface CustomerInfoService {
   List<CustomerInfoDto> getListCustomerInfo(SearchCustomerInfoDto searchCustomerInfoDto);
   Long countCustomerInfo(SearchCustomerInfoDto searchCustomerInfoDto);
   void insertCustomerInfo(CustomerInfoDto customerInfoDto);
   void updateCustomerInfo(CustomerInfoDto customerInfoDto);
   void deleteCustomerInfo(Long customerId);
}
