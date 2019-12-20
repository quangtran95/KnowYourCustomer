package kyc.controller;

import kyc.dto.*;
import kyc.service.CustomerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class CustomerInfoController {
   @Autowired
   CustomerInfoService customerInfoService;

   @RequestMapping(value = "/customer/searchCustomerInfo", method = RequestMethod.POST)
   public @ResponseBody
   ResponseListDto<CustomerInfoDto> getListCustomerInfo(@RequestBody SearchCustomerInfoDto searchCustomerInfoDto) {
      System.out.print("/customer/getListCustomerInfo");
      List<CustomerInfoDto> customerInfoDtoList = customerInfoService.getListCustomerInfo(searchCustomerInfoDto);
      return new ResponseListDto<CustomerInfoDto>(customerInfoDtoList, KycCode.SUCCESS);
   }

   @RequestMapping(value = "/customer/countCustomerInfo", method = RequestMethod.POST)
   public @ResponseBody
   ResponseDto<Long> countCustomerInfo(@RequestBody SearchCustomerInfoDto searchCustomerInfoDto) {
      System.out.print("/customer/countCustomerInfo");
      Long customerInfoNumber = customerInfoService.countCustomerInfo(searchCustomerInfoDto);
      return new ResponseDto<Long>(customerInfoNumber, KycCode.SUCCESS);
   }

   @RequestMapping(value = "/index", method = RequestMethod.GET)
   public ResponseDto getIndex() {
      return new ResponseDto( KycCode.SUCCESS);
   }

   @RequestMapping(value = "/customer/insertCustomerInfo", method = RequestMethod.POST)
   public @ResponseBody
   ResponseDto insertCustomerInfo(@RequestBody CustomerInfoDto customerInfoDto, HttpServletResponse response) {
      System.out.print("/customer/insertCustomerInfo");
      customerInfoService.insertCustomerInfo(customerInfoDto);
      return new ResponseDto(KycCode.SUCCESS);
   }

   @RequestMapping(value = "/customer/updateCustomerInfo", method = RequestMethod.POST)
   public @ResponseBody
   ResponseDto updateCustomerInfo(@RequestBody CustomerInfoDto customerInfoDto) {
      System.out.print("/customer/updateCustomerInfo");
      customerInfoService.updateCustomerInfo(customerInfoDto);
      return new ResponseDto(KycCode.SUCCESS);
   }

   @RequestMapping(value = "/customer/deleteCustomerInfo/{customerId}", method = RequestMethod.POST)
   public @ResponseBody
   ResponseDto deleteCustomerInfo(@PathVariable Long customerId) {
      System.out.print("/customer/deleteCustomerInfo");
      customerInfoService.deleteCustomerInfo(customerId);
      return new ResponseDto(KycCode.SUCCESS);
   }
}
