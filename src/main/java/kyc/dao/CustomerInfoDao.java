package kyc.dao;

import kyc.domain.CustomerInfo;

import java.util.List;

public interface CustomerInfoDao extends GenericDao<CustomerInfo, Long> {
   List<CustomerInfo> getListCustomerInfo(String searchContent);
}
