<!DOCTYPE html>
<html lang="en">
<head>
   <meta charset="UTF-8">
   <title>Know Your Customer</title>
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
   <div>
      <table>
         <tr>
            <td>
               <label>First name: </label>
            </td>
            <td>
               <input type="text" id="input_firstname">
            </td>
         </tr>
         <tr>
            <td>
               <label>Last name: </label>
            </td>
            <td>
               <input type="text" id="input_lastname">
            </td>
         </tr>
         <tr>
            <td>
               <label>Email: </label>
            </td>
            <td>
               <input type="text" id="input_email">
            </td>
         </tr>
         <tr>
            <td>
               <label>Telephone: </label>
            </td>
            <td>
               <input type="text" id="input_telephone">
            </td>
         </tr>
         <tr>
            <td>
               <label>Id Number: </label>
            </td>
            <td>
               <input type="text" id="input_idnumber">
            </td>
         </tr>
         <tr>
            <td>
               <label>Address</label>: </label>
            </td>
            <td>
               <input type="text" id="input_address">
            </td>
         </tr>
      </table>
      <div id="button_group">
         <button type="button" id="button_insert">Insert</button>
         <button type="button" id="button_update">Update</button>
         <button type="button" id="button_delete">Delete</button>
      </div>
   </div>
   <!--<div>-->
      <!--<table>-->
         <!--<tr>-->
            <!--<th>Name</th>-->
            <!--<th>Email</th>-->
            <!--<th>Action</th>-->
            <!--<th>Date</th>-->
         <!--</tr>-->
      <!--</table>-->
   <!--</div>-->
</body>
</html>

<script>
   handleInsertCustomerInfoComplete = function () {
      clearInput();
   };

   handleDeleteCustomerInfoComplete = function () {
   };

   handleUpdateCustomerInfoComplete = function () {
      clearInput();
   };

   clearInput = function () {
      $("#input_firstname").val('');
      $("#input_lastname").val('');
      $("#input_email").val('');
      $("#input_idnumber").val('');
      $("#input_telephone").val('');
      $("#input_address").val('');
   };

   validateFirstNameValue = function (value) {
      if(value === '') {
         return false;
      }
      else {
         return true;
      }
   };

   validateLastNameValue = function (value) {
      if(value === '') {
         return false;
      }
      else {
         return true;
      }
   };

   validateEmailValue = function (value) {
      if(value === '') {
         return false;
      }
      else {
         return true;
      }
   };

   validateIdNumberValue = function (value) {
      if(value === '') {
         return false;
      }
      else {
         return true;
      }
   };

   validateTelephoneNumberValue = function (value) {
      if(value === '') {
         return false
      }
      else {
         return true;
      }
   };

   validateAddressValue = function (value) {
      if(value === '') {
         return false;
      }
      else {
         return true;
      }
   };

   bindEvent = function () {
      $("#input_firstname").bind("blur", function () {
         var valid = validateFirstNameValue($("#input_firstname").val());
         if(!valid) {
            alert('First name is required');
         }
      });

      $("#input_lastname").bind("blur", function () {
         var valid = validateLastNameValue($("#input_lastname").val());
         if(!valid) {
            alert('Last name is required');
         }
      });

      $("#input_email").bind("blur", function () {
         var valid = validateEmailValue($("#input_email").val());
         if(!valid) {
            alert('Email is required');
         }
      });

      $("#input_idnumber").bind("blur", function () {
         var valid = validateIdNumberValue($("#input_idnumber").val());
         if(!valid) {
            alert('Id number is required');
         }
      });

      $("#input_telephone").bind("blur", function () {
         var valid = validateTelephoneNumberValue($("#input_telephone").val());
         if(!valid) {
            alert('Telephone number is required');
         }
      });

      $("#input_address").bind("blur", function () {
         var valid = validateAddressValue($("#input_address").val());
         if(!valid) {
            alert('Address is required');
         }
      });

      $("#button_insert").click(function(){
         insertCustomerInfo();
      });

      $("#button_update").click(function(){
         updateCustomerInfo();
      });

      $("#button_delete").click(function(){
         deleteCustomerInfo();
      });
   };
   
   insertCustomerInfo = function () {
      var firstName = $("#input_firstname").val();
      var lastName = $("#input_lastname").val();
      var email = $("#input_email").val();
      var idNumber = $("#input_idnumber").val();
      var telephoneNumber = $("#input_telephone").val();
      var address = $("#input_address").val();
      
      var validateFirstName = validateFirstNameValue(firstName);
      var validateLastName = validateLastNameValue(lastName);
      var validateEmail = validateEmailValue(email);
      var validateIdNumber = validateIdNumberValue(idNumber);
      var validateTelePhoneNumber = validateTelephoneNumberValue(telephoneNumber);
      var validateAddress = validateAddressValue(address);
      
      var summitValidateMessage = '';
      if(!validateFirstName) {
         summitValidateMessage += 'First name, ';
      }

      if(!validateLastName) {
         summitValidateMessage += 'Last name, ';
      }

      if(!validateEmail) {
         summitValidateMessage += 'Email, ';
      }

      if(!validateIdNumber) {
         summitValidateMessage += 'Id number, ';
      }

      if(!validateTelePhoneNumber) {
         summitValidateMessage += 'Telephone number, ';
      }

      if(!validateAddress) {
         summitValidateMessage += 'Address, ';
      }

      if(validateFirstName && validateLastName && validateEmail && validateIdNumber && validateTelePhoneNumber && validateAddress) {
         var data = {
            firstName: firstName,
            lastName: lastName,
            email: email,
            idNumber: idNumber,
            telephoneNumber: telephoneNumber,
            address: address
         };

         $.ajax({
            type: "POST",
            url: "http://localhost:8080/KnowYourCustomer/customer/insertCustomerInfo",
            data: data,
            success: handleInsertCustomerInfoComplete(),
            error: clearInput(),
            dataType: "json"
         });
      }
      else {
         alert(summitValidateMessage + ' is required');
      }
   };

   deleteCustomerInfo = function (customerId) {
      $.ajax({
         type: "POST",
         url: "http://localhost:8080/KnowYourCustomer/customer/deleteCustomerInfo",
         data: customerId,
         success: handleDeleteCustomerInfoComplete(),
         dataType: "json"
      });
   };

   updateCustomerInfo = function (customerId) {
      var firstName = $("#input_firstname").val();
      var lastName = $("#input_lastname").val();
      var email = $("#input_email").val();
      var idNumber = $("#input_idnumber").val();
      var telephoneNumber = $("#input_telephone").val();
      var address = $("#input_address").val();

      var validateFirstName = validateFirstNameValue(firstName);
      var validateLastName = validateLastNameValue(lastName);
      var validateEmail = validateEmailValue(email);
      var validateIdNumber = validateIdNumberValue(idNumber);
      var validateTelePhoneNumber = validateTelephoneNumberValue(telephoneNumber);
      var validateAddress = validateAddressValue(address);

      var summitValidateMessage = '';
      if(!validateFirstName) {
         summitValidateMessage += 'First name, ';
      }

      if(!validateLastName) {
         summitValidateMessage += 'Last name, ';
      }

      if(!validateEmail) {
         summitValidateMessage += 'Email, ';
      }

      if(!validateIdNumber) {
         summitValidateMessage += 'Id number, ';
      }

      if(!validateTelePhoneNumber) {
         summitValidateMessage += 'Telephone number, ';
      }

      if(!validateAddress) {
         summitValidateMessage += 'Address, ';
      }

      if(validateFirstName && validateLastName && validateEmail && validateIdNumber && validateTelePhoneNumber && validateAddress) {
         var data = {
            id: customerId,
            firstName: firstName,
            lastName: lastName,
            email: email,
            idNumber: idNumber,
            telephoneNumber: telephoneNumber,
            address: address
         };

         $.ajax({
            type: "POST",
            url: "http://localhost:8080/KnowYourCustomer/customer/updateCustomerInfo",
            data: data,
            success: handleUpdateCustomerInfoComplete(),
            error: clearInput(),
            dataType: "json"
         });
      }
      else {
         alert(summitValidateMessage + ' is required');
      }
   };

   populateCustomerInfo = function (customer) {
      if(customer.firstName) {
         $("#input_firstname").val(customer.firstName);
      }

      if(customer.lastName) {
         $("#input_lastname").val(customer.lastName);
      }

      if(customer.email) {
         $("#input_email").val(customer.email);
      }

      if(customer.idNumber) {
         $("#input_idnumber").val(customer.idNumber);
      }

      if(customer.telephoneNumber) {
         $("#input_telephone").val(customer.telephoneNumber);
      }

      if(customer.address) {
         $("#input_address").val(customer.address);
      }
   };

   getListCustomerInfo = function () {
      $.ajax({
         type: "GET",
         url: "http://localhost:8080/KnowYourCustomer/customer/getListCustomerInfo",
         dataType: "json"
      });
   };
</script>

<script>

   getListCustomerInfo();

   bindEvent();
</script>