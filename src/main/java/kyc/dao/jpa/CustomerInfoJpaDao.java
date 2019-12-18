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
   public void insert(CustomerInfo t) {
//      t.setCreateTime(DateTimeUtils.getNowDate());
      t.setUpdateTime(null);

      super.insert(t);
   }

   @Override
   public void update(CustomerInfo t) {
      if (t.getCreateTime() == null) {
         // preserve create time
         CustomerInfo t0 = findById(t.getId());
         if (t0.getCreateTime() == null) {
//            t.setCreateTime(DateTimeUtils.getNowDate());
         } else {
            t.setCreateTime(t0.getCreateTime());
         }
      }
      t.setUpdateTime(null);

      super.update(t);
   }

   @Override
   public List<CustomerInfo> getListCustomerInfo() {
      return getEntityManager().createNativeQuery("SELECT * FROM CUSTOMER_INFO C", CustomerInfo.class)
            .getResultList();
   }
}
