<%-- 
    Document   : index
    Created on : Jan 4, 2018, 9:26:04 PM
    Author     : ekaranja
--%>

<%@page import="com.ams.models.YesNo"%>
<%@page import="java.math.BigDecimal"%>
<%@page import="com.ams.models.Node"%>
<%@page import="com.ams.models.DappUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html  ng-app="assetmanagementModule">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="assets/css/bootstrap.min.css" rel="stylesheet" />
        <link href="DataTables/datatables.css" rel="stylesheet" type="text/css"/>
        <title>Blockchain Technology-In Resource Management</title>
    </head>
    <body ng-controller="AMSController">

        <%
            DappUser currentUser = (DappUser) (session.getAttribute("currentSessionUser"));
            if (currentUser == null) {
                currentUser = new DappUser();

            }

            Node currentNode = (Node) (session.getAttribute("currentNode"));
            if (currentNode == null) {
                currentNode = new Node();
            }

    


        %> 




        <nav class="navbar navbar-inverse navbar-fixed-top" style="border-color: #a71cef;background-color:#27a272">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <!--<a class="navbar-brand" href="#"></a>-->
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li class="active"><a style="background-color:#27a272;" href="#">Distributed County Asset Management System <span class="sr-only">(current)</span></a></li>
                        <li>
                            <a style="background-color:#27a272;color: #fff" href="#!/home">Home</a>

                            <% if (currentUser.getDappRole()!=null && currentUser.getDappRole().getStaff() !=null && currentUser.getDappRole().getStaff()== YesNo.NO) {
                            %>
                        <li>    <a style="background-color:#27a272;color: #fff" href="#">Current CountyCoin Balance:<span><%=currentUser.getBlockChainBalance() == null ? BigDecimal.ZERO : currentUser.getBlockChainBalance()%></span></a></li>


                        <%
                            }
                        %>
                        </li>
                        <% if (currentUser.getUserName() == null) {
                        %>
                        <li><a style="background-color:#27a272; color: #fff" href="" data-toggle="modal" data-target="#loginModal">Login</a></li>


                        <%
                            }
                        %>


                    </ul>


                    <ul class="nav navbar-nav navbar-right">

                        <li class="dropdown">

                            <a style="background-color:#27a272;color: #fff" href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">            
                                <%= currentUser.getUserName() == null ? "My Account" : currentUser.getUserName()%>
                                <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <% if (currentUser.getUserName() == null) {
                                %>
                                <li><a style="background-color:#27a272; color: #fff" href="" data-target="#createAccount" data-toggle="modal"  >Create Account</a></li>


                                <%
                                } else {

                                %>
                                <li><a style="background-color:#27a272; color: #fff" href="#">My Profile</a></li>

                                <li role="separator" class="divider"></li>
                                <li><a style="background-color:#27a272; color: #fff" href="logout">Logout</a></li>
                                    <%                                        }

                                    %>







                            </ul>
                        </li>



                    </ul>



                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>

        <div class="row">
            <div class="col-sm-2">
                <div class="panel panel panel-success" style="margin-top: 50px">
                    <div class="panel-heading">
                        <h3 class="panel-title">Network Operations</h3>
                    </div>
                    <div class="panel-body">
                        <ul class="list-group">

                            <li class="list-group-item">
                                <a href="" ng-click="nodeInfo()" data-toggle="modal" data-target="#joinNetwork" >Join the network</a>
                            </li>


                            </li>
                            <li class="list-group-item"> <a  href="" ng-click="cloneNetwork()">Clone Network</a>

                            </li>
                            <%                                    if (currentUser.getId() != null && currentUser.getDappRole().getStaff() != null && currentUser.getDappRole().getStaff() == YesNo.YES) {
                            %>
                            <li class="list-group-item"> <a href="#!/assetsRegister">Approved Assets Register</a></li>
                            <li class="list-group-item"> <a href="#!/unapprovedAssets">UnApproved Assets Register</a></li>

                            <%
                            } else if (currentUser.getId() != null && currentUser.getDappRole().getStaff() != null && currentUser.getDappRole().getStaff() == YesNo.NO) {

                            %>

                            <li class="list-group-item">
                                <a href="#!/publicExplorer">Public Explorer</a>
                            </li>

                            <%    }

                            %>





                            <!--                            <li class="list-group-item"> <a href="#!/users">Users Register</a></li>-->



                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-sm-8">
                <div ng-view>



                </div>

            </div>
            <div class="col-sm-2">

                <div class="panel panel panel-success" style="margin-top: 50px">
                    <div class="panel-heading">
                        <h3 class="panel-title">Node Information</h3>
                    </div>
                    <div class="panel-body">
                        <ul class="list-group">

                            <li class="list-group-item"> Node Name:<%= currentNode.getNodeName()%></li>
                            <li class="list-group-item"> Node Ip Address:<%= currentNode.getIpAddress()%></li>

                            <li class="list-group-item"> Number of Users:<%= currentNode.getNumberOfUsers()%></li>


                        </ul>
                    </div>
                </div>

            </div>
        </div>

        <div class="row" style="height:100px;background-color:#27a272;margin-top: 150px">
            <div class="col-sm-4">
            </div>

            <div class="col-sm-4">
                <h3>All Rights Reserved &COPY;Francis Mwaniki</h3>
            </div>

            <div class="col-sm-4">
            </div>

        </div>


        <!--         Modal -->
        <div class="modal fade" id="loginModal" role="dialog">
            <div class="modal-dialog">

                <!--                 Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 style="color:red;"><span class="glyphicon glyphicon-lock"></span> Login</h4>
                    </div>
                    <div class="modal-body">
                        <form role="form" method="post" action="login">
                            <div class="form-group">
                                <label for="username"><span class="glyphicon glyphicon-user"></span> Username</label>
                                <input type="text" class="form-control" id="username" placeholder="Enter username"name="username">
                            </div>
                            <div class="form-group">
                                <label for="password"><span class="glyphicon glyphicon-eye-open"></span> Password</label>
                                <input type="password" class="form-control" id="password" placeholder="Enter password" name="password">
                            </div>
                            <div class="checkbox">
                                <label><input type="checkbox" value="" checked>Remember me</label>
                            </div>
                            <button type="submit" class="btn btn-default btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Login</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-default btn-default pull-left" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancel</button>

                    </div>
                </div>
            </div>
        </div>


        <!--create account modal-->
        <div class="modal fade" id="createAccount" role="dialog">
            <div class="modal-dialog">

                <!--                 Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 style="color:red;"><span class="glyphicon glyphicon-lock"></span> Create Account</h4>
                    </div>
                    <div class="modal-body">

                        <form role="form" method="post" action="login">
                            <div class="form-group">
                                <label for="firstname"><span class="glyphicon glyphicon-user"></span> First Name</label>
                                <input type="text" class="form-control" id="firstname" placeholder="Enter first name" ng-model="firstname">
                            </div>
                            <div class="form-group">
                                <label for="lastname"><span class="glyphicon glyphicon-user"></span> Last Name</label>
                                <input type="text" class="form-control" id="lastname" placeholder="Enter last name"ng-model="lastname">
                            </div>
                            <div class="form-group">
                                <label for="username"><span class="glyphicon glyphicon-user"></span> Username</label>
                                <input type="text" class="form-control" id="username" placeholder="Enter username"ng-model="username">
                            </div>


                            <div class="form-group">
                                <label for="password"><span class="glyphicon glyphicon-eye-open"></span> Password</label>
                                <input type="password" class="form-control" id="password" placeholder="Enter password" ng-model="password">
                            </div>
                            <div class="form-group">
                                <label for="cpassword"><span class="glyphicon glyphicon-eye-open"></span>Confirm Password</label>
                                <input type="password" class="form-control" id="cpassword" placeholder="Confirm Password" ng-model="cpassword">
                            </div>

                            <button ng-click="createProfile()"  type="button" class="btn btn-default btn-success btn-block"><span class="glyphicon glyphicon-off"></span> Create Account</button>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-default btn-default pull-left" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancel</button>

                    </div>
                </div>
            </div>
        </div>


        <!--Join network modal-->
        <div class="modal fade" id="joinNetwork" role="dialog">
            <div class="modal-dialog">

                <!--                 Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 style="color:red;"> Join the Network</h4>
                    </div>
                    <div class="modal-body">


                        <div class="form-group">
                            <label> Node Name:</label>
                            <input ng-model="nodeName"  id="nodeName" type="text" class="form-control"  placeholder="Enter node name">
                        </div>
                        <div class="form-group">
                            <label>Node Ip Address:</label>
                            <input ng-model="ipaddress" id="ipaddress" type="text" class="form-control"  placeholder="Enter node Ip Address">
                        </div>
                        <div class="form-group">
                            <label > Clone From Ip:</label>
                            <input type="text" ng-model="cloneIP"    id="cloneIP" class="form-control"  placeholder="Enter clone from Node Ip">
                        </div>
                        <div class="form-group">
                            <label > Number of Users:</label>
                            <input type="text" ng-model="numberOfUsers"  id="numberOfUsers" class="form-control"  placeholder="Enter clone from Node Ip">
                        </div>


                        <button ng-click="saveNodeInfo()" class="btn btn-default btn-success btn-block"> Join Network</button>

                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-default btn-default pull-left" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancel</button>

                    </div>
                </div>
            </div>
        </div>


        <!--Register Asset-->
        <div class="modal fade" id="registerAsset" role="dialog">
            <div class="modal-dialog">

                <!--                 Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 style="color:red;">Asset Registration</h4>
                    </div>
                    <div class="modal-body">


                        <div class="form-group">
                            <label> Asset Code:</label>
                            <input ng-model="assetCode" id="assetCode" type="text" class="form-control"  placeholder="Enter asset Code">
                        </div>
                        <div class="form-group">
                            <label>Asset Name:</label>
                            <input ng-model="assetName" id="assetName" type="text" class="form-control"  placeholder="Enter asser Name">
                        </div>
                        <div class="form-group">
                            <label > Asset Value:</label>
                            <input type="number" ng-model="assetValue" id="assetValue" class="form-control"  placeholder="Enter asset Value">
                        </div>
                        <div class="form-group">
                            <label > Asset Description:</label>
                            <input type="text" ng-model="assetDescription" id="assetDescription" class="form-control"  placeholder="Enter asset Descrition">
                        </div>



                        <button ng-click="registerAsset()" class="btn btn-default btn-success btn-block"> Register Asset</button>

                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-default btn-default pull-left" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancel</button>

                    </div>
                </div>
            </div>
        </div>

        <!--Finance Approval Modal-->
        <div class="modal fade" id="financeApprove" role="dialog">
            <div class="modal-dialog">

                <!--                 Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 style="color:red;">Finance Dept Asset Approval</h4>
                    </div>
                    <div class="modal-body">

                        <input type="hidden" id="financeAssetId">

                        <div class="form-group">
                            <label> Asset Code:</label>
                            <input readonly="readonly" ng-model="financeAssetCode" id="financeAssetCode" type="text" class="form-control"  placeholder="Enter asset Code">
                        </div>
                        <div class="form-group">
                            <label>Asset Name:</label>
                            <input readonly="readonly" ng-model="financeAssetName" id="financeAssetName" type="text" class="form-control"  placeholder="Enter asser Name">
                        </div>
                        <div class="form-group">
                            <label > Asset Value:</label>
                            <input readonly="readonly" type="text" ng-model="financeAssetValue" id="financeAssetValue" class="form-control"  placeholder="Enter asset Value">
                        </div>
                        <div class="form-group">
                            <label> Asset Description:</label>
                            <input readonly="readonly" type="text" ng-model="financeAssetDescription" id="financeAssetDescription" class="form-control"  placeholder="Enter asset Descrition">
                        </div>

                        <div class="form-group">
                            <label > Mode of Payment:</label>
                            <input required type="text" ng-model="modeOfPayment" id="modeOfPayment" class="form-control"  placeholder="Enter payment Mode">
                        </div>
                        <div class="form-group">
                            <label > Comments:</label>
                            <textarea class="form-control" id="financeComments" required="required">
                                
                            </textarea>
                        </div>



                        <button ng-click="financeAssetApproveEvent()" class="btn btn-default btn-success btn-block"> Approve Asset</button>

                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-default btn-default pull-left" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancel</button>

                    </div>
                </div>
            </div>
        </div>

        <!--Store Approval Modal-->
        <div class="modal fade" id="storeApprove" role="dialog">
            <div class="modal-dialog">

                <!--                 Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 style="color:red;"> Store Unit Asset Approval</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" id="storeAssetId">



                        <div class="form-group">
                            <label> Asset Code:</label>
                            <input readonly="readonly" ng-model="storeAssetCode" id="storeAssetCode" type="text" class="form-control"  placeholder="Enter asset Code">
                        </div>
                        <div class="form-group">
                            <label>Asset Name:</label>
                            <input readonly="readonly" ng-model="storeAssetName" id="storeAssetName" type="text" class="form-control"  placeholder="Enter asser Name">
                        </div>
                        <div class="form-group">
                            <label > Asset Value:</label>
                            <input readonly="readonly" type="text" ng-model="storeAssetValue" id="storeAssetValue" class="form-control"  placeholder="Enter asset Value">
                        </div>
                        <div class="form-group">
                            <label> Asset Description:</label>
                            <input readonly="readonly" type="text" ng-model="storeAssetDescription" id="storeAssetDescription" class="form-control"  placeholder="Enter asset Descrition">
                        </div>


                        <div class="form-group">
                            <label > Comments:</label>
                            <textarea class="form-control" id="storeComments">
                                
                            </textarea>
                        </div>



                        <button ng-click="storesAssetApproveEvent()" class="btn btn-default btn-success btn-block"> Approve Asset</button>

                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-default btn-default pull-left" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancel</button>

                    </div>
                </div>
            </div>
        </div>



        <!--BU Approval Modal-->
        <div class="modal fade" id="buApprove" role="dialog">
            <div class="modal-dialog">

                <!--                 Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 style="color:red;">Beneficiary Unit Asset Approval</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" id="buId">



                        <div class="form-group">
                            <label> Asset Code:</label>
                            <input readonly="readonly" ng-model="buassetCode" id="buassetCode" type="text" class="form-control"  placeholder="Enter asset Code">
                        </div>
                        <div class="form-group">
                            <label>Asset Name:</label>
                            <input readonly="readonly" ng-model="buassetName" id="buassetName" type="text" class="form-control"  placeholder="Enter asser Name">
                        </div>
                        <div class="form-group">
                            <label > Asset Value:</label>
                            <input readonly="readonly" type="text" ng-model="buassetValue" id="buassetValue" class="form-control"  placeholder="Enter asset Value">
                        </div>
                        <div class="form-group">
                            <label> Asset Description:</label>
                            <input readonly="readonly" type="text" ng-model="buassetDescription" id="buassetDescription" class="form-control"  placeholder="Enter asset Descrition">
                        </div>
                        <div class="form-group">
                            <label> Beneficiary Name:</label>
                            <input  type="text" ng-model="buBeneficiaryName" id="buBeneficiaryName" class="form-control"  placeholder="Enter asset Descrition">
                        </div>


                        <div class="form-group">
                            <label > Comments:</label>
                            <textarea class="form-control" id="buComments">
                                
                            </textarea>
                        </div>



                        <button ng-click="beneficiaryAssetApproveEvent()" class="btn btn-default btn-success btn-block"> Approve Asset</button>

                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-default btn-default pull-left" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancel</button>

                    </div>
                </div>
            </div>
        </div>

        <!--reject asset Modal-->
        <div class="modal fade" id="rejectAsset" role="dialog">
            <div class="modal-dialog">

                <!--                 Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 style="color:red;"> Reject Asset</h4>
                    </div>
                    <div class="modal-body">



                        <div class="form-group">
                            <label> Asset Code:</label>
                            <input readonly="readonly" ng-model="assetCode" id="assetCode" type="text" class="form-control"  placeholder="Enter asset Code">
                        </div>
                        <div class="form-group">
                            <label>Asset Name:</label>
                            <input readonly="readonly" ng-model="assetName" id="assetName" type="text" class="form-control"  placeholder="Enter asser Name">
                        </div>
                        <div class="form-group">
                            <label > Asset Value:</label>
                            <input readonly="readonly" type="text" ng-model="assetValue" id="assetValue" class="form-control"  placeholder="Enter asset Value">
                        </div>
                        <div class="form-group">
                            <label> Asset Description:</label>
                            <input readonly="readonly" type="text" ng-model="assetDescription" id="assetDescription" class="form-control"  placeholder="Enter asset Descrition">
                        </div>



                        <div class="form-group">
                            <label > reasons for rejection:</label>
                            <textarea class="form-control" id="financeComments">
                                
                            </textarea>
                        </div>



                        <button ng-click="registerAsset()" class="btn btn-default btn-success btn-block"> Reject Asset</button>

                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-default btn-default pull-left" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancel</button>

                    </div>
                </div>
            </div>
        </div>


        <!--finance reject asset Modal-->
        <div class="modal fade" id="financeRejectAsset" role="dialog">
            <div class="modal-dialog">

                <!--                 Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 style="color:red;">Finance Reject Asset</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" id="financeRejectAssetId">


                        <div class="form-group">
                            <label> Asset Code:</label>
                            <input readonly="readonly" ng-model="financeRejectassetCode" id="financeRejectassetCode" type="text" class="form-control"  placeholder="Enter asset Code">
                        </div>
                        <div class="form-group">
                            <label>Asset Name:</label>
                            <input readonly="readonly" ng-model="financeRejectassetName" id="financeRejectassetName" type="text" class="form-control"  placeholder="Enter asser Name">
                        </div>
                        <div class="form-group">
                            <label > Asset Value:</label>
                            <input readonly="readonly" type="text" ng-model="financeRejectassetValue" id="financeRejectassetValue" class="form-control"  placeholder="Enter asset Value">
                        </div>
                        <div class="form-group">
                            <label> Asset Description:</label>
                            <input readonly="readonly" type="text" ng-model="financeRejectassetDescription" id="financeRejectassetDescription" class="form-control"  placeholder="Enter asset Descrition">
                        </div>



                        <div class="form-group">
                            <label > reasons for rejection:</label>
                            <textarea class="form-control" id="financeRejectionReasons">
                                
                            </textarea>
                        </div>



                        <button ng-click="financeAssetRejectEvent()" class="btn btn-default btn-success btn-block"> Reject Asset</button>

                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-default btn-default pull-left" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancel</button>

                    </div>
                </div>
            </div>
        </div>


        <!--stores reject asset Modal-->
        <div class="modal fade" id="storesRejectAsset" role="dialog">
            <div class="modal-dialog">

                <!--                 Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 style="color:red;">Stores Reject Asset</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" id="storesRejectAssetId">


                        <div class="form-group">
                            <label> Asset Code:</label>
                            <input readonly="readonly" ng-model="storesRejectassetCode" id="storesRejectassetCode" type="text" class="form-control"  placeholder="Enter asset Code">
                        </div>
                        <div class="form-group">
                            <label>Asset Name:</label>
                            <input readonly="readonly" ng-model="storesRejectassetName" id="storesRejectassetName" type="text" class="form-control"  placeholder="Enter asser Name">
                        </div>
                        <div class="form-group">
                            <label > Asset Value:</label>
                            <input readonly="readonly" type="text" ng-model="storesRejectassetValue" id="storesRejectassetValue" class="form-control"  placeholder="Enter asset Value">
                        </div>
                        <div class="form-group">
                            <label> Asset Description:</label>
                            <input readonly="readonly" type="text" ng-model="storesRejectassetDescription" id="storesRejectassetDescription" class="form-control"  placeholder="Enter asset Descrition">
                        </div>



                        <div class="form-group">
                            <label > reasons for rejection:</label>
                            <textarea class="form-control" id="storesRejectionReasons">
                                
                            </textarea>
                        </div>



                        <button ng-click="storesAssetRejectEvent()" class="btn btn-default btn-success btn-block"> Reject Asset</button>

                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-default btn-default pull-left" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancel</button>

                    </div>
                </div>
            </div>
        </div>



        <!--Beneficiary Unit rejection asset Modal-->
        <div class="modal fade" id="buRejectAsset" role="dialog">
            <div class="modal-dialog">

                <!--                 Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 style="color:red;">Stores Reject Asset</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" id="buRejectAssetId">


                        <div class="form-group">
                            <label> Asset Code:</label>
                            <input readonly="readonly" ng-model="buRejectassetCode" id="buRejectassetCode" type="text" class="form-control"  placeholder="Enter asset Code">
                        </div>
                        <div class="form-group">
                            <label>Asset Name:</label>
                            <input readonly="readonly" ng-model="buRejectassetName" id="buRejectassetName" type="text" class="form-control"  placeholder="Enter asser Name">
                        </div>
                        <div class="form-group">
                            <label > Asset Value:</label>
                            <input readonly="readonly" type="text" ng-model="buRejectassetValue" id="buRejectassetValue" class="form-control"  placeholder="Enter asset Value">
                        </div>
                        <div class="form-group">
                            <label> Asset Description:</label>
                            <input readonly="readonly" type="text" ng-model="buRejectassetDescription" id="buRejectassetDescription" class="form-control"  placeholder="Enter asset Descrition">
                        </div>



                        <div class="form-group">
                            <label > reasons for rejection:</label>
                            <textarea class="form-control" id="buRejectionReasons">
                                
                            </textarea>
                        </div>



                        <button ng-click="buAssetRejectEvent()" class="btn btn-default btn-success btn-block"> Reject Asset</button>

                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-default btn-default pull-left" data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Cancel</button>

                    </div>
                </div>
            </div>
        </div>




        <script  src="assets/js/jquerydev.js"></script>
        <script src="assets/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="angular.js"></script>
        <script src="assetmanagement/assetmanagement.js"></script>
        <script src="angular-route.js"></script>
        <script src="DataTables/datatables.js" type="text/javascript"></script>
        <script src="bootbox.min.js" type="text/javascript"></script>
        <script>
                            var app = angular.module("assetmanagementModule", ['ngRoute', 'assetmanagement']);

                            app.controller("assetsContoller", ['$scope', '$window', '$http', function ($scope, $window, $http) {
                                    $scope.itemsList = function () {
                                        // $window.alert("itemcode is "+$scope.itemCode);
                                        $http({
                                            url: "/AMS/assets",
                                            method: "get",
                                            params: {
                                                "assetCode": $scope.itemCode,
                                                "assertName": $scope.itemName,
                                                "blockchainCode": $scope.reorderPoint
                                            }
                                        }).then(function (result) {
                                            $window.alert("ajax success" + result.status + " " + result.data);
                                            $scope.itemsDataList = result.data;



                                        }, function (result) {
                                            $window.alert("Ajax fail");

                                        });
                                    };

                                }]);
                            app.config(['$locationProvider', '$routeProvider', function ($locationProvider, $routeProvider) {
                                    $locationProvider.hashPrefix('!');

                                    $routeProvider.otherwise({redirectTo: '/home'});
                                }]);

                            function itemSearch(itemCode) {
                                $.get({
                                    url: 'inventory/inventory/itemSearch',
                                    data: {itemCode: itemCode},
                                    success: function (data) {
                                        //alert(data);
                                        var itemData = JSON.parse(data);
                                        document.getElementById('checkoutItemName').value = itemData.data[0].itemName;
                                        document.getElementById('remainingUnits').value = itemData.data[0].units;
                                    }
                                });
                            }
                            function itemSearch1(itemCode) {
                                $.get({
                                    url: 'inventory/inventory/itemSearch',
                                    data: {itemCode: itemCode},
                                    success: function (data) {
                                        //alert(data);
                                        var itemData = JSON.parse(data);
                                        document.getElementById('checkoutItemName1').value = itemData.data[0].itemName;
                                        document.getElementById('remainingUnits1').value = itemData.data[0].units;
                                    }
                                });
                            }
                            function unitsCompare() {

                                var remainingUnits = parseInt(document.getElementById('remainingUnits1').value);
                                var units = parseInt(document.getElementById('checkoutUnits1').value);


                                if (remainingUnits < units) {
                                    alert("Remaining Units are less than the units To Checkout");
                                    document.getElementById('checkoutUnits1').value = "";
                                }


                            }



        </script>

    </body>
</html>
