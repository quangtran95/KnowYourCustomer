var self = this;
this.listCustomerInfo = [];
this.selectedCustomer;

//variable for getting list data
this.sortPattern = "FirstName";
this.sortAsc = true;
this.searchContent = '';

//variable for paging
this.PAGE_ITEM_NUMBER = 5;
this.currentPage = 0;

//variable for processing load data
this._LIST_CUSTOMER_INFO_LOAD = 0x1;
this._NUMBER_CUSTOMERINFO_LOAD = 0x2;
this._DATA_LOAD_COMPLETE = this._LIST_CUSTOMER_INFO_LOAD | this._NUMBER_CUSTOMERINFO_LOAD;
this.loadData = 0;
this.getListCustomerInfoReturnData;
this.countCustomerInfoReturnData;

//Load data
getListCustomerInfo = function (sortPattern, sortAsc, searchContent, startIndex, limitNumber) {
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

getNumberCustomerInfo = function (sortPattern, sortAsc, searchContent) {
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

checkDataLoading = function () {
   this.loadData = 0;
   var listCustomerInfo = this.getListCustomerInfoReturnData;
   fillCustomerTable(listCustomerInfo);

   var customerInfoNumber = this.countCustomerInfoReturnData;
   generatePagingButton(customerInfoNumber);
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

handleGetListCustomerInfoFail = function (params) {
   alert("Request fail");
};

handleCountCustomerInfoFail = function (params) {
   alert("Request fail");
};

//Render UI
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

//Render Paging
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

//Other function
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