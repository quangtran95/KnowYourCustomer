package kyc.dto;

import java.util.Date;

public class CustomerInfoDto {
   private Long id;
   private String firstName;
   private String lastName;
   private String email;
   private String idNumber;
   private String telephoneNumber;
   private String address;
   private Date updateTime;
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
