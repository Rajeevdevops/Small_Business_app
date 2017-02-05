<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<meta charset="utf-8">
		<title> <tiles:insertAttribute name="title"></tiles:insertAttribute> </title>
		<meta name="generator" content="Bootply" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		
        <link rel="stylesheet" type="text/css" href="css/font-awesome.css"/>
        <link rel="stylesheet" type="text/css" href="css/nanoscroller.css"/>
        <link rel="stylesheet" type="text/css" href="css/theme_styles.css"/>
        <link rel="stylesheet" type="text/css" href="css/jquery-jvectormap-1.2.2.css"/>
        <link rel="stylesheet" type="text/css" href="css/navcss.css"/>
        <link rel="stylesheet" type="text/css" href="css/select2.min.css"/>
		<link href="css/bootstrap.min.css" rel="stylesheet">
		
		<!--[if lt IE 9]>
			<script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->
		<link href="css/styles.css" rel="stylesheet"/>
		<!-- script references -->
		<script src="jquery/jquery.min.js"></script>
		<script src="js/scripts.js"></script>
        <script src="js/jquery.slimscroll.min.js"></script>
        <script src="js/jquery.nanoscroller.min.js"></script>
        <script src="js/demo.js"></script> 
        <script src="js/jquery.sparkline.min.js"></script>
        <script src="js/moment.min.js"></script>
        <script src="js/skycons.js"></script>
        <script src="js/jquery.bpopup.min.js"></script>
        <script src="js/jquery.easing.1.3.js"></script>
        <script src="js/select2.min.js" type = "text/javascript"></script>
       	<script src="js/bootstrap.min.js"></script>
        
        
	</head>
	<body>
<div class="wrapper">
    <div class="box">
          		 <div class="row row-offcanvas row-offcanvas-left">
                         <!-- sidebar -->
            <div class="column col-sm-2 col-xs-1 sidebar-offcanvas" id="sidebar">
              
              	<ul class="nav">
          			<li><a href="#" data-toggle="offcanvas" class="visible-xs text-center"><i class="glyphicon glyphicon-chevron-right"></i></a></li>
            	</ul>
               <span>Welcome</span>
               <br/>  <br/>  <br/>
                <ul class="nav hidden-xs" id="lg-menu">
                   
                   
                   <ul class="nav nav-pills nav-stacked">
                                 <li>
                  <a href="#">
                  <i class="glyphicon glyphicon glyphicon-tasks"></i> Consignee Management
                  </a>
                </li>

                <li  data-toggle="collapse" data-target="#products" class="collapsed active">
                  <a href="#"><i class="glyphicon glyphicon glyphicon-shopping-cart"></i>Stock Management <span class="arrow"></span></a>
                </li>
                <ul class="sub-menu collapse" id="products">
                    <li class="active"><a href="product.html">Product Registration</a></li>
                    <li><a href="viewProducts.html">View Stock</a></li>
                    <li><a href="client.html">Client</a></li>
                    <li><a href="viewClients.html">View Clients</a></li>
                    <li><a href="quotation.html">Quotation</a></li>
                    <li><a href="purchase.html">Purchase</a></li>
                    <li><a href="viewPurchases.html">View Purchase Orders</a></li>
                    <li><a href="viewQuotations.html">View Quotations</a></li>
                    <li><a href="#">Widgets</a></li>
                    <li><a href="#">Bootstrap Model</a></li>
                </ul>


                <li data-toggle="collapse" data-target="#service" class="collapsed">
                  <a href="#"><i class="glyphicon glyphicon glyphicon-tower"></i> Services <span class="arrow"></span></a>
                </li>  
                <ul class="sub-menu collapse" id="service">
                  <li>New Service 1</li>
                  <li>New Service 2</li>
                  <li>New Service 3</li>
                </ul>


                <li data-toggle="collapse" data-target="#new" class="collapsed">
                  <a href="#"><i class="glyphicon glyphicon-list-alt"></i> New <span class="arrow"></span></a>
                </li>
                <ul class="sub-menu collapse" id="new">
                  <li>New New 1</li>
                  <li>New New 2</li>
                  <li>New New 3</li>
                </ul>


                 <li>
                  <a href="#">
                  <i class="glyphicon glyphicon-list-alt"></i> Profile
                  </a>
                  </li>

                 <li>
                  <a href="#">
                  <i class="glyphicon glyphicon-list-alt"></i> Users
                  </a>
                </li>

                                  </ul>
                   
 
                <ul class="list-unstyled hidden-xs" id="sidebar-footer">
                    <li>
                      <a href="http://www.livingwordservices.com"><i class="glyphicon glyphicon-heart-empty"></i>Living Word Services</a>
                    </li>
                </ul>
              
              	<!-- tiny only nav-->
              <ul class="nav visible-xs" id="xs-menu">
                  	<li><a href="#featured" class="text-center"><i class="glyphicon glyphicon-list-alt"></i></a></li>
                    <li><a href="#stories" class="text-center"><i class="glyphicon glyphicon-list"></i></a></li>
                  	<li><a href="#" class="text-center"><i class="glyphicon glyphicon-paperclip"></i></a></li>
                    <li><a href="#" class="text-center"><i class="glyphicon glyphicon-refresh"></i></a></li>
                </ul>
              
            </div>
            <!-- /sidebar -->
          
            <!-- main right col -->
            <div class="column col-sm-10 col-xs-11" id="main">
                
                <!-- top nav -->
              	<div class="navbar navbar-blue navbar-static-top">  
                    <div class="navbar-header">
                      <button class="navbar-toggle" type="button" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle</span>
                        <span class="icon-bar"></span>
          				<span class="icon-bar"></span>
          				<span class="icon-bar"></span>
                      </button>
                      <a href="/" class="navbar-brand logo">b</a>
                  	</div>
                  	<nav class="collapse navbar-collapse" role="navigation">
                    <form class="navbar-form navbar-left">
                        <div class="input-group input-group-sm" style="max-width:360px;">
                          <input type="text" class="form-control" placeholder="Search" name="srch-term" id="srch-term">
                          <div class="input-group-btn">
                            <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
                          </div>
                        </div>
                    </form>
                    <ul class="nav navbar-nav">
                      <li>
                        <a href="#"><i class="glyphicon glyphicon-home"></i> Home</a>
                      </li>
                      <li>
                        <a href="#postModal" role="button" data-toggle="modal"><i class="glyphicon glyphicon-plus"></i> Post</a>
                      </li>
                    <li>
                        <a href="#postModal" role="button" data-toggle="modal"><i class="glyphicon glyphicon-user"></i>User</a>
                      </li>
                    </ul>
                    	</nav>
                </div>
                <!-- /top nav -->
              
                <div class="padding">
                    <div class="full col-sm-9">
                      
                        <!-- content -->                      
                      	<div class="row">
							
							<tiles:insertAttribute name="bodyContent"></tiles:insertAttribute>
							
						</div><!--/row-->
                  
                    </div><!-- /col-9 -->
                </div><!-- /padding -->
            </div>
            <!-- /main -->
          
        </div>
    </div>
</div>


<!--post modal-->
<div id="postModal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
  <div class="modal-dialog">
  <div class="modal-content">
      <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			Update Status
      </div>
      <div class="modal-body">
          <form class="form center-block">
            <div class="form-group">
              <textarea class="form-control input-lg" autofocus placeholder="What do you want to share?"></textarea>
            </div>
          </form>
      </div>
      <div class="modal-footer">
          <div>
          <button class="btn btn-primary btn-sm" data-dismiss="modal" aria-hidden="true">Post</button>
            <ul class="pull-left list-inline"><li><a href=""><i class="glyphicon glyphicon-upload"></i></a></li><li><a href=""><i class="glyphicon glyphicon-camera"></i></a></li><li><a href=""><i class="glyphicon glyphicon-map-marker"></i></a></li></ul>
		  </div>	
      </div>
  </div>
  </div>
</div>
	
	</body>
</html>