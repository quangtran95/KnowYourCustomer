package kyc.service;

import kyc.dao.CustomerInfoDao;
import kyc.domain.CustomerInfo;
import kyc.dto.CustomerInfoDto;
import kyc.dto.SearchCustomerInfoDto;
import kyc.utils.DateTimeUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@Service("customerInfoService")
public class CustomerInfoServiceImp extends GenericService implements CustomerInfoService {

   @Autowired
   @Qualifier("customerInfoJpaDao")
   CustomerInfoDao customerInfoDao;

   public List<CustomerInfoDto> getListCustomerInfo(SearchCustomerInfoDto searchCustomerInfoDto) {
      List<CustomerInfo> customerInfoList = customerInfoDao.getListCustomerInfo(searchCustomerInfoDto);
      List<CustomerInfoDto> customerInfoDtoList = mapper.mapCollection(customerInfoList, CustomerInfoDto.class);
      System.out.println("Get List Customer Info Complete");
      return customerInfoDtoList;
   }

   @Override
   public Long countCustomerInfo(SearchCustomerInfoDto searchCustomerInfoDto) {
      Long customerInfoNumber = customerInfoDao.countCustomerInfo(searchCustomerInfoDto);
      System.out.println("Count Customer Info Complete");
      return customerInfoNumber;
   }

   @Override
   public String exportCustomerInfo(SearchCustomerInfoDto searchCustomerInfoDto) {
      Long customerInfoNumber = customerInfoDao.countCustomerInfo(searchCustomerInfoDto);
      searchCustomerInfoDto.setStartIndex(0);
      searchCustomerInfoDto.setLimitNumber(customerInfoNumber.intValue());

      List<CustomerInfoDto> customerInfoList = getListCustomerInfo(searchCustomerInfoDto);

      HSSFWorkbook workbook = new HSSFWorkbook();
      HSSFSheet sheet = workbook.createSheet("KYC");
      sheet.setColumnWidth(0, 5000);
      sheet.setColumnWidth(1, 5000);
      sheet.setColumnWidth(2, 5000);
      sheet.setColumnWidth(3, 5000);
      sheet.setColumnWidth(4, 5000);
      sheet.setColumnWidth(5, 5000);
      sheet.setColumnWidth(6, 14000);

      CellStyle cellStyleTitle = sheet.getWorkbook().createCellStyle();
      Font fontTitle = sheet.getWorkbook().createFont();
      fontTitle.setBoldweight((short) 2);
      fontTitle.setFontHeightInPoints((short) 25);
      cellStyleTitle.setFont(fontTitle);

      CellStyle cellStyleData= sheet.getWorkbook().createCellStyle();
      Font fontData = sheet.getWorkbook().createFont();
      fontData.setFontHeightInPoints((short) 18);
      cellStyleData.setFont(fontData);

      Integer rowNum = 0;
      //Title
      Row rowTitle = sheet.createRow(rowNum);
      Cell cellFirstNameTitle = rowTitle.createCell(0);
      cellFirstNameTitle.setCellValue("First Name");
      cellFirstNameTitle.setCellStyle(cellStyleTitle);

      Cell cellLastNameTitle = rowTitle.createCell(1);
      cellLastNameTitle.setCellValue("Last Name");
      cellLastNameTitle.setCellStyle(cellStyleTitle);

      Cell cellEmailTitle = rowTitle.createCell(2);
      cellEmailTitle.setCellValue("Email");
      cellEmailTitle.setCellStyle(cellStyleTitle);

      Cell cellIdNumberTitle = rowTitle.createCell(3);
      cellIdNumberTitle.setCellValue("Id Number");
      cellIdNumberTitle.setCellStyle(cellStyleTitle);

      Cell cellTelephoneTitle = rowTitle.createCell(4);
      cellTelephoneTitle.setCellValue("Telephone");
      cellTelephoneTitle.setCellStyle(cellStyleTitle);

      Cell cellAddressTitle = rowTitle.createCell(5);
      cellAddressTitle.setCellValue("Address");
      cellAddressTitle.setCellStyle(cellStyleTitle);

      Cell cellDateTitle = rowTitle.createCell(6);
      cellDateTitle.setCellValue("Date");
      cellDateTitle.setCellStyle(cellStyleTitle);

      //Data
      rowNum++;
      for(CustomerInfoDto customerInfoDto : customerInfoList) {
         Row row = sheet.createRow(rowNum);
         Cell cellFirstName = row.createCell(0);
         cellFirstName.setCellValue(customerInfoDto.getFirstName());
         cellFirstName.setCellStyle(cellStyleData);

         Cell cellLastName = row.createCell(1);
         cellLastName.setCellValue(customerInfoDto.getLastName());
         cellLastName.setCellStyle(cellStyleData);

         Cell cellEmail = row.createCell(2);
         cellEmail.setCellValue(customerInfoDto.getEmail());
         cellEmail.setCellStyle(cellStyleData);

         Cell cellIdNumber = row.createCell(3);
         cellIdNumber.setCellValue(customerInfoDto.getIdNumber());
         cellIdNumber.setCellStyle(cellStyleData);

         Cell cellTelephone = row.createCell(4);
         cellTelephone.setCellValue(customerInfoDto.getTelephoneNumber());
         cellTelephone.setCellStyle(cellStyleData);

         Cell cellAddress = row.createCell(5);
         cellAddress.setCellValue(customerInfoDto.getAddress());
         cellAddress.setCellStyle(cellStyleData);

         Cell cellDate = row.createCell(6);
         String formatDate = DateTimeUtils.formatDate(customerInfoDto.getUpdateTime(), "dd MMM, hh:mm aa (yyyy)");
         cellDate.setCellValue(formatDate);
         cellDate.setCellStyle(cellStyleData);

         rowNum++;
      }

      try {
         String projectPath = System.getProperty("user.dir");
         File persistent = new File(projectPath + "\\KYC");
         if (!persistent.exists()){
            persistent.mkdir();
         }

         String fileName = persistent.getPath() + "\\KYC_" + DateTimeUtils.getCurrentDateTimeYYYYMMDDHHMMSS() + ".xls";

         FileOutputStream out = new FileOutputStream(new File(fileName));
         workbook.write(out);
         out.close();

         return fileName;
      }
      catch (Exception ex) {
         StringWriter sw = new StringWriter();
         ex.printStackTrace(new PrintWriter(sw));
         System.out.println("Cannot create file: " +sw.toString());
         return "";
      }
   }

   @Override
   @Transactional(propagation = Propagation.REQUIRED)
   public void insertCustomerInfo(CustomerInfoDto customerInfoDto) {
      CustomerInfo customerInfo = mapper.map(customerInfoDto, CustomerInfo.class);
      customerInfoDao.insert(customerInfo);
      System.out.println("Insert Customer Info Complete");
   }

   @Override
   @Transactional(propagation = Propagation.REQUIRED)
   public void updateCustomerInfo(CustomerInfoDto customerInfoDto) {
      CustomerInfo customerInfo = customerInfoDao.findById(customerInfoDto.getId());
      if(customerInfo != null) {
         customerInfo.setFirstName(customerInfoDto.getFirstName());
         customerInfo.setLastName(customerInfoDto.getLastName());
         customerInfo.setEmail(customerInfoDto.getEmail());
         customerInfo.setIdNumber(customerInfoDto.getIdNumber());
         customerInfo.setTelephoneNumber(customerInfoDto.getTelephoneNumber());
         customerInfo.setAddress(customerInfoDto.getAddress());

         customerInfoDao.update(customerInfo);
      }
      System.out.println("Update Customer Info Complete");
   }

   @Override
   @Transactional(propagation = Propagation.REQUIRED)
   public void deleteCustomerInfo(Long customerId) {
      CustomerInfo customerInfo = customerInfoDao.findById(customerId);
      if(customerInfo != null) {
         customerInfoDao.delete(customerId);
      }
      System.out.println("Delete Customer Info Complete");
   }
}
