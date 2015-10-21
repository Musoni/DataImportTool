<html>
<head>
<title>Data Import Tool</title>
<style>
*{margin:0;padding:0;}
body{font-family:Helvetica Neue, Arial, sans-serif;background: #EEE;}
div#title{color:#777;text-align: center;font-size: 2em;margin: 10px auto 0 auto;padding: 25px;font-weight: bold;}
div#container{width:75%;margin:0 auto;}
div#content {padding: 20px;}
div.step{padding:15px;margin-bottom:20px;background-image: -webkit-linear-gradient(top,#fff 0,#f8f8f8 100%);background-image: linear-gradient(to bottom,#fff 0,#f8f8f8 100%);background-repeat: repeat-x;filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ffffffff', endColorstr='#fff8f8f8', GradientType=0);filter: progid:DXImageTransform.Microsoft.gradient(enabled=false);border-radius: 4px;-webkit-box-shadow: inset 0 1px 0 rgba(255,255,255,.15),0 1px 5px rgba(0,0,0,.075);box-shadow: inset 0 1px 0 rgba(255,255,255,.15),0 1px 5px rgba(0,0,0,.075);}
div.step .stepCount {float: left;width: 70px;font-weight: bold;color:#777;}
div.step .stepCount span{font-size: 12px;}
.step .stepContent {float: left;width: 85%;}
div.step form {margin: 15px 0;}
.clearfix:after {content: ".";display: block;height: 0;clear: both;visibility: hidden;}
.clearfix {display: inline-block;}
* html .clearfix {height: 1%;} /* Hides from IE-mac \*/
.clearfix {display: block;}
.btn {color: #fff;background-color: #428bca;border-color: #357ebd;background-image: -webkit-linear-gradient(top,#428bca 0,#2d6ca2 100%);background-image: linear-gradient(to bottom,#428bca 0,#2d6ca2 100%);filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#ff428bca', endColorstr='#ff2d6ca2', GradientType=0);filter: progid:DXImageTransform.Microsoft.gradient(enabled=false);background-repeat: repeat-x;border-color: #2b669a;padding: 5px 10px;font-size: 12px;line-height: 1.5;border-radius: 3px;display: inline-block;margin-bottom: 0;font-weight: 400;text-align: center;vertical-align: middle;cursor: pointer;background-image: none;border: 1px solid transparent;white-space: nowrap;-webkit-user-select: none;-moz-user-select: none;-ms-user-select: none;}
.btn:hover{color: #fff;background-color: #3276b1;border-color: #285e8e;}
fieldset {border: 0;margin: 0 0 15px 0;}
</style>
</head>
<body>
<div id="title">Data Import Tool</div>

<div id="container">
  <div id="content">

	<div class="step clearfix">
		<div class="stepCount">Step 1 :</div>
		<div class="stepContent">
			<p>Enter your offices and staff through the front-end app provided with MifosX.</p>
		</div>
	</div>
	
	<div class="step clearfix">
	  	<div class="stepCount">Step 2 :</div>
	  	<div class="stepContent">
		  	<p>Download template to help facilitate Clients Import.</p>
		    <form method="get" action="download" >
		     <input type="hidden" name="template" value="client" > 
		     <fieldset>Select Client Type : &nbsp;&nbsp;
		     <input type="radio" name="clientType" value="individual"> Individual &nbsp;&nbsp;
             <input type="hidden" name="tenantIdentifier" value="<%= request.getParameter("tenantIdentifier") %>" >
		     <input type="submit" value="Download" class="btn" />
		    </form>
			<form method="post" action="import?tenantIdentifier=<%= request.getParameter("tenantIdentifier") %>" enctype="multipart/form-data">
		            <input type="file" name="file"/>
		            <input type="submit" class="btn"/>
			</form>
		</div>
	</div>
	
            <div class="step clearfix">
        <div class="stepCount">Step 3:</div> 
        <div class="stepContent">
            <p>Download template to add customer identifiers. </p>
            <form method="get" action="download" >
             <input type="hidden" name="template" value="clientIdentifier" > 
             <input type="hidden" name="tenantIdentifier" value="<%= request.getParameter("tenantIdentifier") %>" >
             <input type="submit" value="Download" class="btn"  />
            </form>
            <form method="post" action="import?tenantIdentifier=<%= request.getParameter("tenantIdentifier") %>" enctype="multipart/form-data">
                    <input type="file" name="file"/>
                    <input type="submit" class="btn"/>
            </form>
        </div>
        
        
    </div>
	
	<div class="step clearfix">
		<div class="stepCount">Step 4:</div> 
		<div class="stepContent">
			<p>Download template to help facilitate Groups Import.</p>
		    <form method="get" action="download" >
		     <input type="hidden" name="template" value="groups" > 
		     <input type="hidden" name="tenantIdentifier" value="<%= request.getParameter("tenantIdentifier") %>" >
             <input type="submit" value="Download" class="btn"  />
		    </form>
			<form method="post" action="import?tenantIdentifier=<%= request.getParameter("tenantIdentifier") %>" enctype="multipart/form-data">
		            <input type="file" name="file"/>
		            <input type="submit" class="btn"/>
			</form>
		</div>
	</div>	
	
	<div class="step clearfix">
		<div class="stepCount">Step 5:</div> 
		<div class="stepContent">
			<p>
			Enter your Currency Configuration, Funds, Charges, Loan Product configuration (Administration -&gt;
			Organisation) and Payment Types (Administration -&gt; System -&gt; View Code -&gt; Add/Edit Code Value) 
			 through the UI.</p>
		</div>
	</div>
	
	<div class="step clearfix">
		<div class="stepCount">Step 6:</div>
		<div class="stepContent">
			<p>Download template to help facilitate Loan Import. </p>
		    <p>For quick import of outstanding balances of each loan, use the entire template <b>OR</b> leave the last 3 columns empty and proceed to Step 5 for importing complete repayment history for each loan.</p>
			<form method="get" action="download" >
		     <input type="hidden" name="template" value="loan" > 
		     <input type="hidden" name="tenantIdentifier" value="<%= request.getParameter("tenantIdentifier") %>" >
             <input type="submit" value="Download" class="btn"  />
		    </form>
		    <form method="post" action="import?tenantIdentifier=<%= request.getParameter("tenantIdentifier") %>" enctype="multipart/form-data">
		            <input type="file" name="file"/>
		            <input type="submit" class="btn"/>
			</form>
		</div>
	</div>
	
	<div class="step clearfix">
	    <div class="stepCount">Step 7a:<br/><span>(Optional)</span></div>
	    <div class="stepContent">
	    	<p>Download template to help facilitate import of Individual/JLG Loan Repayment History.</p>
		    <form method="get" action="download" >
		     <input type="hidden" name="template" value="loanRepaymentHistory" > 
		     <input type="hidden" name="tenantIdentifier" value="<%= request.getParameter("tenantIdentifier") %>" >
             <input type="submit" value="Download" class="btn"  />
		    </form>
		    <form method="post" action="import?tenantIdentifier=<%= request.getParameter("tenantIdentifier") %>" enctype="multipart/form-data">
		            <input type="file" name="file"/>
		            <input type="submit" class="btn"/>
			</form>
		</div>
	</div>

	  <div class="step clearfix">
		  <div class="stepCount">Step 7b:<br/><span>(Optional)</span></div>
		  <div class="stepContent">
			  <p>Download template to help facilitate import of Group Loan Repayment History.</p>
			  <form method="get" action="download" >
				  <input type="hidden" name="template" value="groupLoanRepaymentHistory" >
				  <input type="hidden" name="tenantIdentifier" value="<%= request.getParameter("tenantIdentifier") %>" >
                  <input type="submit" value="Download" class="btn"  />
			  </form>
			  <form method="post" action="import?tenantIdentifier=<%= request.getParameter("tenantIdentifier") %>" enctype="multipart/form-data">
				  <input type="file" name="file"/>
				  <input type="submit" class="btn"/>
			  </form>
		  </div>
	  </div>
	
	<div class="step clearfix">
		<div class="stepCount">Step 8:</div> 
		<div class="stepContent">
			<p>Enter your Savings Product configuration (Administration -&gt; Organisation) through the UI.</p>
		</div>
	</div>
	
	<div class="step clearfix">
		<div class="stepCount">Step 9:</div> 
		<div class="stepContent">
			<p>Download template to help facilitate Savings Import. </p>
		    <p>For quick import of current balances of each savings account, enter the current balance as Minimum Required Opening Balance
		     <b>OR</b> proceed to Step 8 for importing complete transaction history for each savings account.</p>
			<form method="get" action="download" >
		     <input type="hidden" name="template" value="savings" > 
		     <input type="hidden" name="tenantIdentifier" value="<%= request.getParameter("tenantIdentifier") %>" >
             <input type="submit" value="Download" class="btn"  />
		    </form>
		    <form method="post" action="import?tenantIdentifier=<%= request.getParameter("tenantIdentifier") %>" enctype="multipart/form-data">
		            <input type="file" name="file"/>
		            <input type="submit" class="btn"/>
			</form>
		</div>
	</div>
	
	<div class="step clearfix">
	    <div class="stepCount">Step 10:<br/><span>(Optional)</span></div>
	    <div class="stepContent">
		    <p>Download template to help facilitate import of Savings Transaction History.</p>
		    <form method="get" action="download" >
		     <input type="hidden" name="template" value="savingsTransactionHistory" > 
		     <input type="hidden" name="tenantIdentifier" value="<%= request.getParameter("tenantIdentifier") %>" >
             <input type="submit" value="Download" class="btn"  />
		    </form>
		    <form method="post" action="import?tenantIdentifier=<%= request.getParameter("tenantIdentifier") %>" enctype="multipart/form-data">
		            <input type="file" name="file"/>
		            <input type="submit" class="btn"/>
			</form>
		</div>
	</div>
	
	<div class="step clearfix">
		<div class="stepCount">Step 11:</div> 
		<div class="stepContent">
			<p>Download template to help Add Journal Entries. </p>
		    <form method="get" action="download" >
		     <input type="hidden" name="template" value="journalentries" > 
		     <input type="hidden" name="tenantIdentifier" value="<%= request.getParameter("tenantIdentifier") %>" >
             <input type="submit" value="Download" class="btn"  />
		    </form>
		    <form method="post" action="import?tenantIdentifier=<%= request.getParameter("tenantIdentifier") %>" enctype="multipart/form-data">
		            <input type="file" name="file"/>
		            <input type="submit" class="btn"/>
			</form>
		</div>
		</div>
		<div class="step clearfix">
		<div class="stepCount">Step :12</div> 
		<div class="stepContent">
			<p>Download template to help Add Guarantor. </p>
		    <form method="get" action="download" >
		     <input type="hidden" name="template" value="guarantor" > 
		     <input type="hidden" name="tenantIdentifier" value="<%= request.getParameter("tenantIdentifier") %>" >
             <input type="submit" value="Download" class="btn"  />
		    </form>
		    <form method="post" action="import?tenantIdentifier=<%= request.getParameter("tenantIdentifier") %>" enctype="multipart/form-data">
		            <input type="file" name="file"/>
		            <input type="submit" class="btn"/>
			</form>
		</div>
		
	</div>
      <div class="step clearfix">
		<div class="stepCount">Step 20:</div> 
		<div class="stepContent">
			<p>Add client Details datatable. </p>
		    <form method="get" action="download" >
                         <fieldset>Select Datatable : &nbsp;&nbsp;
		     <input type="radio" name="datatableName" value="ml_client_details"> Client Details &nbsp;&nbsp;
		     <input type="radio" name="datatableName" value="ml_client_next_of_kin"> Next of Kin </fieldset>
		     <input type="hidden" name="template" value="datatable" > 
		     <input type="submit" value="Download" class="btn"  />
		    </form>
		    <form method="post" action="import" enctype="multipart/form-data">
		            <input type="file" name="file"/>
		            <input type="submit" class="btn"/>
			</form>
		</div>
		
		
	</div>
	
</div>
</div>
</body>
</html>