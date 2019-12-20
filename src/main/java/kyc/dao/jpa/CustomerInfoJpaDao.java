package kyc.dao.jpa;

import kyc.dao.CustomerInfoDao;
import kyc.domain.CustomerInfo;
import kyc.dto.SearchCustomerInfoDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("customerInfoJpaDao")
public class CustomerInfoJpaDao extends GenericJpaDao<CustomerInfo, Long> implements CustomerInfoDao {

   public CustomerInfoJpaDao() {
      super(CustomerInfo.class);
   }

   @Override
   public List<CustomerInfo> getListCustomerInfo(SearchCustomerInfoDto searchCustomerInfoDto) {
      StringBuilder sqlBuilder = new StringBuilder();
      sqlBuilder.append("SELECT * FROM CUSTOMER_INFO C ");
      sqlBuilder.append("WHERE ");

      //Search content with like %%
      sqlBuilder.append("C.FIRST_NAME like :search ");
      sqlBuilder.append("OR ");
      sqlBuilder.append("C.LAST_NAME like :search ");
      sqlBuilder.append("OR ");
      sqlBuilder.append("C.EMAIL like :search ");
      sqlBuilder.append("OR ");
      sqlBuilder.append("C.ID_NUMBER like :search ");
      sqlBuilder.append("OR ");
      sqlBuilder.append("C.TELEPHONE_NUMBER like :search ");
      sqlBuilder.append("OR ");
      sqlBuilder.append("C.ADDRESS like :search ");

      //Group By
      sqlBuilder.append("GROUP BY C.ID ");

      //Order By
      if (searchCustomerInfoDto.getSortPattern() != null) {
         switch (searchCustomerInfoDto.getSortPattern()) {
            case "FirstName":
               sqlBuilder.append("ORDER BY C.FIRST_NAME ");
               break;
            case "LastName":
               sqlBuilder.append("ORDER BY C.LAST_NAME ");
               break;
            case "Email":
               sqlBuilder.append("ORDER BY C.EMAIL ");
               break;
            case "IdNumber":
               sqlBuilder.append("ORDER BY C.ID_NUMBER ");
               break;
            case "Telephone":
               sqlBuilder.append("ORDER BY C.TELEPHONE_NUMBER ");
               break;
            case "Address":
               sqlBuilder.append("ORDER BY C.ADDRESS ");
               break;
            default:
               sqlBuilder.append("ORDER BY C.ID ");
         }

         //ASC
         if (searchCustomerInfoDto.isSortAsc()) {
            sqlBuilder.append("ASC ");
         } else {
            sqlBuilder.append("DESC ");
         }
      }
      System.out.println("[SQL] " + sqlBuilder.toString());

      return getEntityManager().createNativeQuery( sqlBuilder.toString(), CustomerInfo.class)
            .setParameter("search", "%" + searchCustomerInfoDto.getSearchContent() + "%")
            .getResultList();
   }
}
