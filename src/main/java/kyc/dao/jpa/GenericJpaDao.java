package kyc.dao.jpa;

import kyc.dao.GenericDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.Date;

public abstract class GenericJpaDao<T, ID extends Serializable> implements GenericDao<T, ID> {
   public static String LIKE_CHARACTER = "%";

   private final Logger logger = LoggerFactory.getLogger(GenericJpaDao.class);

   private final Class<T> persistentClass;

   private EntityManager entityManager;

   @PersistenceContext
   public void setEntityManager(EntityManager entityManager) {
      this.entityManager = entityManager;
   }

   public EntityManager getEntityManager() {
      return entityManager;
   }

   public GenericJpaDao(final Class<T> persistentClass) {
      this.persistentClass = persistentClass;
   }

   @Override
   public T findById(ID id) {
      return entityManager.find(persistentClass, id);
   }

   @Override
   public void delete(ID id) {
      T deleteObject = entityManager.find(persistentClass, id);
      entityManager.remove(deleteObject);
   }

   @Override
   public void delete(T t) {
      entityManager.remove(t);
   }

   @Override
   public void insert(T t)  {
      try {
         Class[] cArg = new Class[1];
         cArg[0] = Date.class;
         Method funcUpdateTime = persistentClass.getMethod("setUpdateTime",cArg);
         Method funcCreateTime = persistentClass.getMethod("setCreateTime",cArg);
//         funcCreateTime.invoke(t , DateTimeUtils.getNowDate());
         funcUpdateTime.invoke(t,new Object[]{null});
      }
      catch (Exception ex){
         StringWriter sw = new StringWriter();
         ex.printStackTrace(new PrintWriter(sw));
         logger.debug("[GenericJpaDao][Insert] Can not invoke method setUpdateTime or setCreateTime of class : "
               + t.getClass().getName() + ", error :" + sw.toString());
      }
      entityManager.persist(t);

   }

   @Override
   public void update(T t)  {
      try {
         Class[] cArg = new Class[1];
         cArg[0] = Date.class;
         Method funcSetUpdateTime = persistentClass.getMethod("setUpdateTime",cArg);
         Method funcSetCreateTime = persistentClass.getMethod("setCreateTime",cArg);
         Method funcGetCreateTime = persistentClass.getMethod("getCreateTime");
         Method funcGetId = persistentClass.getMethod("getId");
         Object createTimeValue = funcGetCreateTime.invoke(t);
         if (createTimeValue == null) {
            Object id = funcGetId.invoke(t);
            T tInDB = findById((ID) id);
            if (funcGetCreateTime.invoke(tInDB) == null) {
//               funcSetCreateTime.invoke(t, DateTimeUtils.getNowDate());
            } else {
               funcSetCreateTime.invoke(t, funcGetCreateTime.invoke(tInDB));
            }
         }
         funcSetUpdateTime.invoke(t, new Object[]{null});
      }
      catch (Exception ex){
         StringWriter sw = new StringWriter();
         ex.printStackTrace(new PrintWriter(sw));
         logger.debug("[GenericJpaDao][Update] Can not invoke method setUpdateTime or setCreateTime of class : "
               + t.getClass().getName() + ", error :" + sw.toString());
      }
      entityManager.merge(t);

   }

   @Override
   public void refresh(T t) {
      entityManager.refresh(t);
   }

   @Override
   public void flush() {
      entityManager.flush();
   }
}
