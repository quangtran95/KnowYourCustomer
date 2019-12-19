package kyc.dao.jpa;

import kyc.dao.CustomerInfoDao;
import kyc.domain.CustomerInfo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("customerInfoJpaDao")
public class CustomerInfoJpaDao extends GenericJpaDao<CustomerInfo, Long> implements CustomerInfoDao {

   public CustomerInfoJpaDao() {
      super(CustomerInfo.class);
   }

   @Override
   public List<CustomerInfo> getListCustomerInfo(String searchContent) {
      return getEntityManager().createNativeQuery("SELECT * FROM CUSTOMER_INFO C WHERE C.FIRST_NAME like :search OR C.LAST_NAME like :search OR C.EMAIL like :search OR C.ID_NUMBER like :search OR C.TELEPHONE_NUMBER like :search OR C.ADDRESS like :search GROUP BY C.ID", CustomerInfo.class)
            .setParameter("search", "%" + searchContent + "%")
            .getResultList();
   }
}
