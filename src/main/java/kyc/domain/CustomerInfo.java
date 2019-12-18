package kyc.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "CUSTOMER_INFO")
public class CustomerInfo implements Serializable {

   @Id
   @Access(AccessType.PROPERTY)
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ID")
   private Long id;

   @Column(name = "FIRST_NAME")
   private String firstName;

   @Column(name = "LAST_NAME")
   private String lastName;

   @Column(name = "EMAIL")
   private String email;

   @Column(name = "ID_NUMBER")
   private String idNumber;

   @Column(name = "TELEPHONE_NUMBER")
   private String telephoneNumber;

   @Column(name = "ADDRESS")
   private String address;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "UPDATE_TIME")
   private Date updateTime;

   @Temporal(TemporalType.TIMESTAMP)
   @Column(name = "CREATE_TIME")
   private Date createTime;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getIdNumber() {
      return idNumber;
   }

   public void setIdNumber(String idNumber) {
      this.idNumber = idNumber;
   }

   public String getTelephoneNumber() {
      return telephoneNumber;
   }

   public void setTelephoneNumber(String telephoneNumber) {
      this.telephoneNumber = telephoneNumber;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public Date getUpdateTime() {
      return updateTime;
   }

   public void setUpdateTime(Date updateTime) {
      this.updateTime = updateTime;
   }

   public Date getCreateTime() {
      return createTime;
   }

   public void setCreateTime(Date createTime) {
      this.createTime = createTime;
   }
}
