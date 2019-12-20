package kyc.dao;

import kyc.domain.CustomerInfo;
import kyc.dto.SearchCustomerInfoDto;

import java.util.List;

public interface CustomerInfoDao extends GenericDao<CustomerInfo, Long> {
   List<CustomerInfo> getListCustomerInfo(SearchCustomerInfoDto searchCustomerInfoDto);
   Long countCustomerInfo(SearchCustomerInfoDto searchCustomerInfoDto);
}
