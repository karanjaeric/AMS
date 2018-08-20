<%-- 
    Document   : unapprovedAssets
    Created on : Feb 28, 2018, 8:52:12 AM
    Author     : ekaranja
--%>

<%@page import="com.ams.models.YesNo"%>
<%@page import="com.ams.models.DappUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    DappUser currentUser = (DappUser) (session.getAttribute("currentSessionUser"));
    if (currentUser == null) {
        currentUser = new DappUser();
    }
%> 
<div class="content" style="margin-top: 60px">
    <div class="container-fluid">
        <div class="row">
            <h3>Unapproved Assets Register</h3>
            <input type="text" placeholder="search By Name">
            <input type="text" placeholder="search By Asset Code">
            <!--<input type="number" placeholder="search Limit" step="1" max="20" min="1">-->

            <button ng-click="unapproveAssetsList()" class="btn btn-info" style="background-color: #5bc0de;color:  #041203;">Load Assets</button>

            <table class="table table-responsive table-striped" datatable="ng" dt-options="dtOptions">
                <tr>
                    <th>Asset Name</th>
                    <th>Asset Code</th>
                    <th>Asset Description</th>
                   
                        <% if (currentUser.getDappRole() != null && currentUser.getDappRole().getAccessFinanceDept() == YesNo.YES) {
                        %>
                    <th>Finance DPT</th>
                        <%
                            }
                        %>

                    <% if (currentUser.getDappRole() != null && currentUser.getDappRole().getAccessStoresDept() == YesNo.YES) {
                    %>
                    <th>Stores DPT</th>
                        <%
                            }
                        %>


                    <% if (currentUser.getDappRole() != null && currentUser.getDappRole().getBeneficiaryUnitDept() == YesNo.YES) {
                    %>
                    <th>Beneficiary Unit</th>
                        <%
                            }
                        %>



                </tr>

                <tr ng-repeat="asset in assetsDataList">
                    <td>{{asset.assetName}}</td>
                    <td>{{asset.assetCode}}</td>
                    <td>{{asset.assetDescription}}</td>
                  

                    <% if (currentUser.getDappRole() != null && currentUser.getDappRole().getAccessFinanceDept() == YesNo.YES) {
                    %>
                    <td><button  type="button" class="btn btn-sm btn-success" ng-click="financeApproveAsset(asset.id, asset.assetName, asset.assetCode, asset.assetDescription, asset.assetValue)">Approve</button>&nbsp;<button type="button" class="btn btn-sm btn-danger" ng-click="financeUnitRejectAsset(asset.id, asset.assetName, asset.assetCode, asset.assetDescription, asset.assetValue)">Reject</button></td>

                    <%
                        }
                    %>



                    <% if (currentUser.getDappRole() != null && currentUser.getDappRole().getAccessStoresDept() == YesNo.YES) {
                    %>
                    <td><button type="button" class="btn btn-sm btn-success" ng-click="storesApproveAsset(asset.id, asset.assetName, asset.assetCode, asset.assetDescription, asset.assetValue)">Approve</button>&nbsp;<button type="button" class="btn btn-sm btn-danger" ng-click="storesUnitRejectAsset(asset.id, asset.assetName, asset.assetCode, asset.assetDescription, asset.assetValue)">Reject</button></td>

                    <%
                        }
                    %>

                    <% if (currentUser.getDappRole() != null && currentUser.getDappRole().getBeneficiaryUnitDept() == YesNo.YES) {
                    %>
                    <td><button type="button" class="btn btn-sm btn-success" ng-click="beneficiaryUnitApproveAsset(asset.id, asset.assetName, asset.assetCode, asset.assetDescription, asset.assetValue)">Approve</button>&nbsp;<button type="button" class="btn btn-sm btn-danger" ng-click="buRejectAsset(asset.id, asset.assetName, asset.assetCode, asset.assetDescription, asset.assetValue)">Reject</button></td>

                    <%
                        }
                    %>


                </tr>
            </table>
        </div>
    </div>
</div>
