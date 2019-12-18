package kyc.dto;

import java.io.Serializable;

public class ResponseDto<ResponseObject> implements Serializable {
   private int code;

   private String message;

   private ResponseObject responseObject;

   public ResponseDto(int code) {
      this.code = code;
   }

   public ResponseDto(ResponseObject responseObject, int code) {
      this.code = code;
      this.responseObject = responseObject;
   }

   public ResponseDto(int code, String message) {
      this.code = code;
      this.message = message;
   }

   public ResponseDto(ResponseObject responseObject, int code, String message) {
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

   public ResponseObject getResponseObject() {
      return responseObject;
   }

   public void setResponseObject(ResponseObject responseObject) {
      this.responseObject = responseObject;
   }
}
