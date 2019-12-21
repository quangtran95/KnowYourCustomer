<!DOCTYPE html>
<html lang="en">
<head>
   <meta charset="UTF-8">
   <title>Know Your Customer</title>
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
   <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" >
   <link rel="stylesheet" type="text/css" href="customer-info.css">.
   <script type="text/javascript" src="validation.js"></script>
   <script type="text/javascript" src="customer-info-input.js"></script>
   <script type="text/javascript" src="customer-info-table.js"></script>
</head>
<body>
   <div>
      <table style="margin: auto;">
         <tr>
            <td>
               <label>First name: </label>
            </td>
            <td>
               <input type="text" id="input_firstname" class="form-control" maxlength="100">
            </td>
         </tr>
         <tr>
            <td>
               <label>Last name: </label>
            </td>
            <td>
               <input type="text" id="input_lastname" class="form-control" maxlength="100">
            </td>
         </tr>
         <tr>
            <td>
               <label>Email: </label>
            </td>
            <td>
               <input type="text" id="input_email" class="form-control" maxlength="100">
            </td>
         </tr>
         <tr>
            <td>
               <label>Telephone: </label>
            </td>
            <td>
               <input type="number" min="0" id="input_telephone" class="form-control" maxlength="100">
            </td>
         </tr>
         <tr>
            <td>
               <label>Id Number: </label>
            </td>
            <td>
               <input type="number" min="0" id="input_idnumber" class="form-control" maxlength="100">
            </td>
         </tr>
         <tr>
            <td>
               <label>Address</label>: </label>
            </td>
            <td>
               <input type="text" id="input_address" class="form-control" maxlength="100">
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
   <div style="min-height: 160px;">
      <table id="kyc_customer_info_table_header">
         <tr>
            <th class="kyc-customer-info-header fa fa-sort-asc" id="kyc_first_name_header"> First Name</th>
            <th class="kyc-customer-info-header fa " id="kyc_last_name_header"> Last Name</th>
            <th class="kyc-customer-info-header fa " id="kyc_email_header"> Email</th>
            <th class="kyc-customer-info-header fa " id="kyc_id_number_header"> Id Number</th>
            <th class="kyc-customer-info-header fa " id="kyc_telephone_header"> Telephone</th>
            <th class="kyc-customer-info-header fa " id="kyc_address_header"> Address</th>
            <th class="kyc-customer-info-header fa " id="kyc_date_header"> Date</th>
         </tr>
      </table>
      <table id="kyc_customer_info_table" style="max-height: 130px;" class="table-striped">
      </table>
   </div>
   <div id="kyc_paging_group">

   </div>
</body>
</html>
<script>
   bindEvent();
</script>