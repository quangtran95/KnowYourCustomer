package kyc.controller;

import kyc.dto.CustomerInfoDto;
import kyc.dto.KycCode;
import kyc.dto.ResponseDto;
import kyc.dto.ResponseListDto;
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

   @RequestMapping(value = "/customer/getListCustomerInfo", method = RequestMethod.GET)
   public ResponseListDto<CustomerInfoDto> getListCustomerInfo(HttpServletResponse response) {
      List<CustomerInfoDto> customerInfoDtoList = customerInfoService.getListCustomerInfo();
      response.setContentType("application/json");
      return new ResponseListDto<CustomerInfoDto>(customerInfoDtoList, KycCode.SUCCESS);
   }

   @RequestMapping(value = "/index", method = RequestMethod.GET)
   public ResponseDto getIndex() {
      return new ResponseDto( KycCode.SUCCESS);
   }

   @RequestMapping(value = "/customer/insertCustomerInfo", method = RequestMethod.POST)
   public ResponseDto insertCustomerInfo(@RequestBody CustomerInfoDto customerInfoDto) {
      customerInfoService.insertCustomerInfo(customerInfoDto);
      return new ResponseDto(KycCode.SUCCESS);
   }

   @RequestMapping(value = "/customer/updateCustomerInfo", method = RequestMethod.POST)
   public ResponseDto updateCustomerInfo(@RequestBody CustomerInfoDto customerInfoDto) {
      customerInfoService.updateCustomerInfo(customerInfoDto);
      return new ResponseDto(KycCode.SUCCESS);
   }

   @RequestMapping(value = "/customer/deleteCustomerInfo", method = RequestMethod.POST)
   public ResponseDto deleteCustomerInfo(@RequestBody Long customerId) {
      customerInfoService.deleteCustomerInfo(customerId);
      return new ResponseDto(KycCode.SUCCESS);
   }
}
