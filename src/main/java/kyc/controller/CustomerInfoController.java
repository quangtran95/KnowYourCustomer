package kyc.controller;

import kyc.dto.*;
import kyc.service.CustomerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Controller
public class CustomerInfoController {
   @Autowired
   CustomerInfoService customerInfoService;

   @RequestMapping(value = "/customer/searchCustomerInfo", method = RequestMethod.POST)
   public @ResponseBody
   ResponseListDto<CustomerInfoDto> getListCustomerInfo(@RequestBody SearchCustomerInfoDto searchCustomerInfoDto) {
      System.out.println("/customer/getListCustomerInfo");
      List<CustomerInfoDto> customerInfoDtoList = customerInfoService.getListCustomerInfo(searchCustomerInfoDto);
      return new ResponseListDto<CustomerInfoDto>(customerInfoDtoList, KycCode.SUCCESS);
   }

   @RequestMapping(value = "/customer/countCustomerInfo", method = RequestMethod.POST)
   public @ResponseBody
   ResponseDto<Long> countCustomerInfo(@RequestBody SearchCustomerInfoDto searchCustomerInfoDto) {
      System.out.println("/customer/countCustomerInfo");
      Long customerInfoNumber = customerInfoService.countCustomerInfo(searchCustomerInfoDto);
      return new ResponseDto<Long>(customerInfoNumber, KycCode.SUCCESS);
   }

   @RequestMapping(value = "/customer/exportCustomerInfo", method = RequestMethod.POST)
   public @ResponseBody
   ResponseDto exportCustomerInfo(@RequestBody SearchCustomerInfoDto searchCustomerInfoDto, HttpServletResponse response, HttpServletRequest request) throws IOException {
      System.out.println("/customer/exportCustomerInfo");
      String fileXlsName = customerInfoService.exportCustomerInfo(searchCustomerInfoDto);
//      File file = new File(fileXlsName);
//      if (file.exists() && file.isFile()) {
//         String filePath = file.getAbsolutePath();
//         int length = 0;
//         ServletOutputStream outStream = response.getOutputStream();
//         ServletContext context = request.getSession().getServletContext();
//         String mimetype = context.getMimeType(filePath);
//
//         // sets response content type
//         if (mimetype == null) {
//            mimetype = "application/octet-stream";
//         }
//         response.setContentType(mimetype);
//         response.setContentLength((int) file.length());
//         String fileName = (new File(filePath)).getName();
//
//         // sets HTTP header
//         response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
//
//         byte[] byteBuffer = new byte[4096];
//         DataInputStream in = new DataInputStream(new FileInputStream(file));
//
//         // reads the file's bytes and writes them to the response stream
//         while ((in != null) && ((length = in.read(byteBuffer)) != -1)) {
//            outStream.write(byteBuffer, 0, length);
//         }
//
//         in.close();
//         outStream.close();
//         return new ResponseDto(KycCode.SUCCESS);
//      } else {
//         return new ResponseDto(KycCode.FAILED);
//      }
      return new ResponseDto(KycCode.SUCCESS);
   }

   @RequestMapping(value = "/customer/insertCustomerInfo", method = RequestMethod.POST)
   public @ResponseBody
   ResponseDto insertCustomerInfo(@RequestBody CustomerInfoDto customerInfoDto, HttpServletResponse response) {
      System.out.println("/customer/insertCustomerInfo");
      customerInfoService.insertCustomerInfo(customerInfoDto);
      return new ResponseDto(KycCode.SUCCESS);
   }

   @RequestMapping(value = "/customer/updateCustomerInfo", method = RequestMethod.POST)
   public @ResponseBody
   ResponseDto updateCustomerInfo(@RequestBody CustomerInfoDto customerInfoDto) {
      System.out.println("/customer/updateCustomerInfo");
      customerInfoService.updateCustomerInfo(customerInfoDto);
      return new ResponseDto(KycCode.SUCCESS);
   }

   @RequestMapping(value = "/customer/deleteCustomerInfo/{customerId}", method = RequestMethod.POST)
   public @ResponseBody
   ResponseDto deleteCustomerInfo(@PathVariable Long customerId) {
      System.out.println("/customer/deleteCustomerInfo");
      customerInfoService.deleteCustomerInfo(customerId);
      return new ResponseDto(KycCode.SUCCESS);
   }
}
