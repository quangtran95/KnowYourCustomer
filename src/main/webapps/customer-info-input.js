handleInsertCustomerInfoComplete = function () {
   clearInput();
   alert("Insert customer complete");
};

handleDeleteCustomerInfoComplete = function () {
   clearInput();
   $("#kyc_customer_info_table tr").css('background-color', 'unset');
   alert("Delete customer complete");
   self.selectedCustomer = null;
};

handleUpdateCustomerInfoComplete = function () {
   clearInput();
   $("#kyc_customer_info_table tr").css('background-color', 'unset');
   alert("Update customer complete");
   self.selectedCustomer = null;
};

clearInput = function () {
   $("#input_firstname").val('');
   $("#input_lastname").val('');
   $("#input_email").val('');
   $("#input_idnumber").val('');
   $("#input_telephone").val('');
   $("#input_address").val('');
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
         contentType: "application/json",
         url: "http://localhost:8080/KnowYourCustomer/customer/insertCustomerInfo",
         data: JSON.stringify(data),
         success: function (data) {
            handleInsertCustomerInfoComplete(data);
         },
         error: clearInput(),
         dataType: "json"
      });
   }
   else {
      alert(summitValidateMessage + ' is required');
   }
};

deleteCustomerInfo = function () {
   if(self.selectedCustomer) {
      $.ajax({
         type: "POST",
         url: "http://localhost:8080/KnowYourCustomer/customer/deleteCustomerInfo/" + self.selectedCustomer.id,
         success: function (data) {
            handleDeleteCustomerInfoComplete(data);
         },
         dataType: "json"
      });
   }
   else {
      alert("Please select a customer first");
   }
};

updateCustomerInfo = function () {
   if(self.selectedCustomer) {
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
      if (!validateFirstName) {
         summitValidateMessage += 'First name, ';
      }

      if (!validateLastName) {
         summitValidateMessage += 'Last name, ';
      }

      if (!validateEmail) {
         summitValidateMessage += 'Email, ';
      }

      if (!validateIdNumber) {
         summitValidateMessage += 'Id number, ';
      }

      if (!validateTelePhoneNumber) {
         summitValidateMessage += 'Telephone number, ';
      }

      if (!validateAddress) {
         summitValidateMessage += 'Address, ';
      }

      if (validateFirstName && validateLastName && validateEmail && validateIdNumber && validateTelePhoneNumber && validateAddress) {
         var data = Object.assign({}, self.selectedCustomer);
         data.firstName = firstName;
         data.lastName = lastName;
         data.email = email;
         data.idNumber = idNumber;
         data.telephoneNumber = telephoneNumber;
         data.address = address;

         $.ajax({
            type: "POST",
            contentType: "application/json",
            url: "http://localhost:8080/KnowYourCustomer/customer/updateCustomerInfo",
            data: JSON.stringify(data),
            success: function (data) {
               handleUpdateCustomerInfoComplete(data);
            },
            error: clearInput(),
            dataType: "json"
         });
      }
      else {
         alert(summitValidateMessage + ' is required');
      }
   }
   else {
      alert("Please select a customer first");
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