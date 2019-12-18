package kyc.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, ID extends Serializable> {

   T findById(ID id);

   void insert(T t);

   void update(T t);

   void delete(ID id);

   void delete(T t);

   void refresh(T t);

   void flush();

}
