package kyc.dto;

import java.io.Serializable;
import java.util.List;

public class ResponseListDto<ResponseObject> implements Serializable {
   private int code;

   private String message;

   private List<ResponseObject> responseObject;

   public ResponseListDto(int code) {
      this.code = code;
   }

   public ResponseListDto(List<ResponseObject> responseObject, int code) {
      this.code = code;
      this.responseObject = responseObject;
   }

   public ResponseListDto(int code, String message) {
      this.code = code;
      this.message = message;
   }

   public ResponseListDto(List<ResponseObject> responseObject, int code, String message) {
      this.code = code;
      this.message = message;
      this.responseObject = responseObject;
   }

   public int getCode() {
      return code;
   }

   public void setCode(int code) {
      this.code = code;
   }

   public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public List<ResponseObject> getResponseObject() {
      return responseObject;
   }

   public void setResponseObject(List<ResponseObject> responseObject) {
      this.responseObject = responseObject;
   }
}
