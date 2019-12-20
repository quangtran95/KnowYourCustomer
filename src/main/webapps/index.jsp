<!DOCTYPE html>
<html lang="en">
<head>
   <meta charset="UTF-8">
   <title>Know Your Customer</title>
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
   <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" >
   <link rel="stylesheet" type="text/css" href="customer-info.css">.
</head>
<body>
   <div>
      <table style="margin: auto;">
         <tr>
            <td>
               <label>First name: </label>
            </td>
            <td>
               <input type="text" id="input_firstname" class="form-control">
            </td>
         </tr>
         <tr>
            <td>
               <label>Last name: </label>
            </td>
            <td>
               <input type="text" id="input_lastname" class="form-control">
            </td>
         </tr>
         <tr>
            <td>
               <label>Email: </label>
            </td>
            <td>
               <input type="text" id="input_email" class="form-control">
            </td>
         </tr>
         <tr>
            <td>
               <label>Telephone: </label>
            </td>
            <td>
               <input type="text" id="input_telephone" class="form-control">
            </td>
         </tr>
         <tr>
            <td>
               <label>Id Number: </label>
            </td>
            <td>
               <input type="text" id="input_idnumber" class="form-control">
            </td>
         </tr>
         <tr>
            <td>
               <label>Address</label>: </label>
            </td>
            <td>
               <input type="text" id="input_address" class="form-control">
            </td>
         </tr>
      </table>
      <div id="button_group" style="text-align: center;">
         <button type="button" id="button_insert" class="btn btn-primary">Insert</button>
         <button type="button" id="button_update" class="btn btn-primary">Update</button>
         <button type="button" id="button_delete" class="btn btn-primary">Delete</button>
         
      </div>
      </hr>
      <div>
         <input id="kyc_search" type="text" placeholder="Search...">
         <button type="button" id="button_search" class="btn btn-primary">Search</button>
      </div>
   </div>
   <div>
      <table id="kyc_customer_info_table_header">
         <tr>
            <th class="kyc-customer-info-header fa fa-sort-alpha-asc" id="kyc_first_name_header"> First Name</th>
            <th class="kyc-customer-info-header fa " id="kyc_last_name_header"> Last Name</th>
            <th class="kyc-customer-info-header fa " id="kyc_email_header"> Email</th>
            <th class="kyc-customer-info-header fa " id="kyc_id_number_header"> Id Number</th>
            <th class="kyc-customer-info-header fa " id="kyc_telephone_header"> Telephone</th>
            <th class="kyc-customer-info-header fa " id="kyc_address_header"> Address</th>
         </tr>
      </table>
      <table id="kyc_customer_info_table" style="min-height: 130px;" class="table-striped">
      </table>
      <div id="kyc_paging_group">

      </div>
   </div>
</body>
</html>

<script>
   var self = this;
   this.listCustomerInfo = [];
   this.selectedCustomer;

   this.sortPattern = "FirstName";
   this.sortAsc = true;
   this.searchContent = '';

   this.PAGE_ITEM_NUMBER = 5;
   this.currentPage = 0;

   this._LIST_CUSTOMER_INFO_LOAD = 0x1;
   this._NUMBER_CUSTOMERINFO_LOAD = 0x2;

   this._DATA_LOAD_COMPLETE = this._LIST_CUSTOMER_INFO_LOAD | this._NUMBER_CUSTOMERINFO_LOAD;
   this.loadData = 0;

   this.getListCustomerInfoReturnData;
   this.countCustomerInfoReturnData;

   fillCustomerTable = function (listCustomerInfo) {
      this.listCustomerInfo = listCustomerInfo;
      renderCustomerTable(listCustomerInfo);
      bindEventCusomerTable();
   };

   renderCustomerTable = function (listCustomerInfo) {
      var tableHtml = '';

      for(index in listCustomerInfo) {
         var row =   '<tr cutomer-id="' + listCustomerInfo[index].id + '" style="vertical-align: top; height: 26px;">' +
                        '<td style="width: 150px; padding-left: 15px;">' + listCustomerInfo[index].firstName + '</td>' +
                        '<td style="width: 150px; padding-left: 15px;">' + listCustomerInfo[index].lastName + '</td>' +
                        '<td style="width: 150px; padding-left: 15px;">' + listCustomerInfo[index].email + '</td>' +
                        '<td style="width: 150px; padding-left: 15px;">' + listCustomerInfo[index].idNumber + '</td>' +
                        '<td style="width: 150px; padding-left: 15px;">' + listCustomerInfo[index].telephoneNumber + '</td>' +
                        '<td style="width: 150px; padding-left: 15px;">' + listCustomerInfo[index].address + '</td>' +
                     '</tr>';
         tableHtml += row;
      }

      $("#kyc_customer_info_table").html("");
      $("#kyc_customer_info_table").html(tableHtml);
   };

   bindEventCusomerTable = function () {
      $( "#kyc_customer_info_table tr").unbind( "click" );
      $("#kyc_customer_info_table tr").bind("click", function () {
         $("#kyc_customer_info_table tr").css('background-color', 'unset');
         $(this).css('background-color', '#8080803d');
         var customerId = parseInt($(this).attr('cutomer-id'));

         for(index in self.listCustomerInfo) {
            if(self.listCustomerInfo[index].id === customerId) {
               fillCustomerInput(self.listCustomerInfo[index]);
               self.selectedCustomer = self.listCustomerInfo[index];
               break;
            }
         }
      });

      $( "#kyc_first_name_header").unbind( "click" );
      $("#kyc_first_name_header").bind("click", function () {
         if(self.sortPattern === "FirstName"){
            self.sortAsc = !self.sortAsc;
         }
         else {
            self.sortPattern = "FirstName";
            self.sortAsc = true;
         }
         updateSortUi("kyc_customer_info_table_header", "kyc_first_name_header", self.sortAsc);
         var startIndex = self.currentPage * self.PAGE_ITEM_NUMBER;
         getListCustomerInfo(self.sortPattern, self.sortAsc, self.searchContent, startIndex, self.PAGE_ITEM_NUMBER);
         getNumberCustomerInfo(self.sortPattern, self.sortAsc, self.searchContent);
      });

      $( "#kyc_last_name_header").unbind( "click" );
      $("#kyc_last_name_header").bind("click", function () {
         if(self.sortPattern === "LastName"){
            self.sortAsc = !self.sortAsc;
         }
         else {
            self.sortPattern = "LastName";
            self.sortAsc = true;
         }
         updateSortUi("kyc_customer_info_table_header", "kyc_last_name_header", self.sortAsc);
         var startIndex = self.currentPage * self.PAGE_ITEM_NUMBER;
         getListCustomerInfo(self.sortPattern, self.sortAsc, self.searchContent, startIndex, self.PAGE_ITEM_NUMBER);
         getNumberCustomerInfo(self.sortPattern, self.sortAsc, self.searchContent);
      });

      $( "#kyc_email_header").unbind( "click" );
      $("#kyc_email_header").bind("click", function () {
         if(self.sortPattern === "Email"){
            self.sortAsc = !self.sortAsc;
         }
         else {
            self.sortPattern = "Email";
            self.sortAsc = true;
         }
         updateSortUi("kyc_customer_info_table_header", "kyc_email_header", self.sortAsc);
         var startIndex = self.currentPage * self.PAGE_ITEM_NUMBER;
         getListCustomerInfo(self.sortPattern, self.sortAsc, self.searchContent, startIndex, self.PAGE_ITEM_NUMBER);
         getNumberCustomerInfo(self.sortPattern, self.sortAsc, self.searchContent);
      });

      $( "#kyc_id_number_header").unbind( "click" );
      $("#kyc_id_number_header").bind("click", function () {
         if(self.sortPattern === "IdNumber"){
            self.sortAsc = !self.sortAsc;
         }
         else {
            self.sortPattern = "IdNumber";
            self.sortAsc = true;
         }
         updateSortUi("kyc_customer_info_table_header", "kyc_id_number_header", self.sortAsc);
         var startIndex = self.currentPage * self.PAGE_ITEM_NUMBER;
         getListCustomerInfo(self.sortPattern, self.sortAsc, self.searchContent, startIndex, self.PAGE_ITEM_NUMBER);
         getNumberCustomerInfo(self.sortPattern, self.sortAsc, self.searchContent);
      });

      $( "#kyc_telephone_header").unbind( "click" );
      $("#kyc_telephone_header").bind("click", function () {
         if(self.sortPattern === "Telephone"){
            self.sortAsc = !self.sortAsc;
         }
         else {
            self.sortPattern = "Telephone";
            self.sortAsc = true;
         }
         updateSortUi("kyc_customer_info_table_header", "kyc_telephone_header", self.sortAsc);
         var startIndex = self.currentPage * self.PAGE_ITEM_NUMBER;
         getListCustomerInfo(self.sortPattern, self.sortAsc, self.searchContent, startIndex, self.PAGE_ITEM_NUMBER);
         getNumberCustomerInfo(self.sortPattern, self.sortAsc, self.searchContent);
      });

      $( "#kyc_address_header").unbind( "click" );
      $("#kyc_address_header").bind("click", function () {
         if(self.sortPattern === "Address"){
            self.sortAsc = !self.sortAsc;
         }
         else {
            self.sortPattern = "Address";
            self.sortAsc = true;
         }
         updateSortUi("kyc_customer_info_table_header", "kyc_address_header", self.sortAsc);
         var startIndex = self.currentPage * self.PAGE_ITEM_NUMBER;
         getListCustomerInfo(self.sortPattern, self.sortAsc, self.searchContent, startIndex, self.PAGE_ITEM_NUMBER);
         getNumberCustomerInfo(self.sortPattern, self.sortAsc, self.searchContent);
      });
   };

   updateSortUi = function (tableId, headerId, sortAsc) {
      $("#" + tableId + " tr th").removeClass("fa-sort-alpha-asc fa-sort-alpha-desc");
      if(sortAsc === true) {
         $("#" + headerId).addClass("fa-sort-alpha-asc");
      }
      else {
         $("#" + headerId).addClass("fa-sort-alpha-desc");
      }
   };

   getListCustomerInfo = function (sortPattern, sortAsc, seachContent, startIndex, limitNumber) {
      var data = {
         searchContent: searchContent,
         sortPattern: sortPattern,
         sortAsc: sortAsc,
         startIndex: startIndex,
         limitNumber: limitNumber
      };

      $.ajax({
         type: "POST",
         contentType: "application/json",
         url: "http://localhost:8080/KnowYourCustomer/customer/searchCustomerInfo",
         data: JSON.stringify(data),
         success: function(params) {
            handleGetListCustomerInfoComplete(params);
         },
         error: function () {
            handleGetListCustomerInfoFail();
         },
         dataType: "json"
      });
   };

   getNumberCustomerInfo = function (sortPattern, sortAsc, seachContent) {
      var data = {
         searchContent: searchContent,
         sortPattern: sortPattern,
         sortAsc: sortAsc
      };

      $.ajax({
         type: "POST",
         contentType: "application/json",
         url: "http://localhost:8080/KnowYourCustomer/customer/countCustomerInfo",
         data: JSON.stringify(data),
         success: function(params) {
            handleCountCustomerInfoComplete(params);
         },
         error: function () {
            handleCountCustomerInfoFail();
         },
         dataType: "json"
      });
   };

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

   handleGetListCustomerInfoComplete = function (params) {
      if(params.code === 0) {
         this.getListCustomerInfoReturnData = params.responseObject;

         this.loadData |= this._LIST_CUSTOMER_INFO_LOAD;
         if(this.loadData === this._DATA_LOAD_COMPLETE) {
            this.checkDataLoading();
         }
      }
      else {
         alert("Get List Customer Info fail");
      }
   };

   handleCountCustomerInfoComplete = function (params) {
      if(params.code === 0) {
         this.countCustomerInfoReturnData = params.responseObject;

         this.loadData |= this._NUMBER_CUSTOMERINFO_LOAD;
         if(this.loadData === this._DATA_LOAD_COMPLETE) {
            this.checkDataLoading();
         }
      }
      else {
         alert("Count Customer Info fail");
      }
   };

   checkDataLoading = function () {
      this.loadData = 0;
      var listCustomerInfo = this.getListCustomerInfoReturnData;
      fillCustomerTable(listCustomerInfo);

      var customerInfoNumber = this.countCustomerInfoReturnData;
      generatePagingButton(customerInfoNumber);
   };

   generatePagingButton = function (customerInfoNumber) {
      var numberPaging = Math.ceil(customerInfoNumber / this.PAGE_ITEM_NUMBER);

      var pagingsHtml = '';
      for(var i = 0; i < numberPaging; i++ ){
         var pageButton = '<div style="border: 1px solid blue; width: 25px; text-align: center; cursor: pointer; display: inline-block; margin: 7px;" class="kyc-paging" paging-id = "' + i + '">' + (i+1) + '</div>';
         pagingsHtml += pageButton;
      }

      $("#kyc_paging_group").html(pagingsHtml);
      $(".kyc-paging").css("background-color", "unset");
      $("#kyc_paging_group [paging-id='" + this.currentPage + "']").css("background-color", "#007bff3b");
      bindEventPagings();
   };

   bindEventPagings = function() {
      $(".kyc-paging").unbind("click");
      $(".kyc-paging").bind("click", function () {
         self.currentPage = parseInt($(this).attr("paging-id"));
         var startIndex = self.currentPage * self.PAGE_ITEM_NUMBER;
         getListCustomerInfo(self.sortPattern, self.sortAsc, self.searchContent, startIndex, self.PAGE_ITEM_NUMBER);
         getNumberCustomerInfo(self.sortPattern, self.sortAsc, self.searchContent);
      });
   };

   handleGetListCustomerInfoFail = function (params) {
      alert("Request fail");
   };
   handleCountCustomerInfoFail = function (params) {
      alert("Request fail");
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
      // if(value === '') {
      //    return false;
      // }
      // else {
      //    return true;
      // }
      return true;
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
      // if(value === '') {
      //    return false;
      // }
      // else {
      //    return true;
      // }
      return true;
   };

   bindEvent = function () {
      $( "#input_firstname" ).unbind( "blur" );
      $("#input_firstname").bind("blur", function () {
         var valid = validateFirstNameValue($("#input_firstname").val());
         if(!valid) {
            alert('First name is required');
         }
      });

      $( "#input_lastname" ).unbind( "blur" );
      $("#input_lastname").bind("blur", function () {
         var valid = validateLastNameValue($("#input_lastname").val());
         if(!valid) {
            alert('Last name is required');
         }
      });

      $( "#input_email" ).unbind( "blur" );
      $("#input_email").bind("blur", function () {
         var valid = validateEmailValue($("#input_email").val());
         if(!valid) {
            alert('Email is required');
         }
      });

      $( "#input_idnumber" ).unbind( "blur" );
      $("#input_idnumber").bind("blur", function () {
         var valid = validateIdNumberValue($("#input_idnumber").val());
         if(!valid) {
            alert('Id number is required');
         }
      });

      $( "#input_telephone" ).unbind( "blur" );
      $("#input_telephone").bind("blur", function () {
         var valid = validateTelephoneNumberValue($("#input_telephone").val());
         if(!valid) {
            alert('Telephone number is required');
         }
      });

      $( "#input_address" ).unbind( "blur" );
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

      $("#button_search").click(function(){
         var searchContent = $("#kyc_search").val();
         self.searchContent = searchContent;
         self.currentPage = 0;
         var startIndex = self.currentPage * self.PAGE_ITEM_NUMBER;
         getListCustomerInfo(self.sortPattern, self.sortAsc, self.searchContent, startIndex, self.PAGE_ITEM_NUMBER);
         getNumberCustomerInfo(self.sortPattern, self.sortAsc, self.searchContent);
         self.selectedCustomer = null;
         clearInput();
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

   searchCustomerInfo = function (searchContent) {
      var data = {
         searchContent: searchContent
      };

      $.ajax({
         type: "POST",
         contentType: "application/json",
         url: "http://localhost:8080/KnowYourCustomer/customer/searchCustomerInfo",
         data: JSON.stringify(data),
         success: function(params) {
            handleGetListCustomerInfoComplete(params);
         },
         error: function () {
            handleGetListCustomerInfoFail();
         },
         dataType: "json"
      });
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

   fillCustomerInput = function (customer) {
      if(customer) {
         $("#input_firstname").val(customer.firstName);
         $("#input_lastname").val(customer.lastName);
         $("#input_email").val(customer.email);
         $("#input_telephone").val(customer.telephoneNumber);
         $("#input_idnumber").val(customer.idNumber);
         $("#input_address").val(customer.address);
      }
   };
</script>

<script>
   bindEvent();
</script>