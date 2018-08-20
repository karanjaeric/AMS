'use strict';

var mainApp = angular.module('assetmanagement', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/home', {
                    templateUrl: 'assetmanagement/home.html'

                }).when('/assetsRegister', {
                    templateUrl: 'assetmanagement/assetsRegister.html',
                    controller: 'AMSController'


                })
                    .when('/publicExplorer', {
                    templateUrl: 'assetmanagement/publicExplorer.html',
                    controller: 'AMSController'


                }).when('/unapprovedAssets', {
                    templateUrl: 'assetmanagement/unapprovedAssets.jsp',
                    controller: 'AMSController'


                }).when('/feesmanagement', {
                    templateUrl: 'inventory/feesmanagement.html'


                }).when('/profile', {
                    templateUrl: 'inventory/profile.html'


                }).when('/studentsmanagement', {
                    templateUrl: 'inventory/studentsmanagement.html'




                }).when('/gamesandsports', {
                    templateUrl: 'inventory/gamesandsports.html'




                }).when('/admin', {
                    templateUrl: 'inventory/admin.html'




                });
            }]);

mainApp.controller('AMSController', ['$http', '$window', '$scope', function ($http, $window, $scope) {
        $scope.clicktest = function () {
            $window.alert("testing");
        };

        $scope.itemsList = function () {
            // $window.alert("itemcode is "+$scope.itemCode);
            $http({
                url: "/sms/inventory",
                method: "get"
//                    params: {
//                        "itemCode": $scope.itemCode,
//                        "itemName": $scope.itemName,
//                        "reorderPoint": $scope.reorderPoint
//                    }
            }).then(function (result) {
                //$window.alert("ajax success" + result.status + " " + result.data);
                $scope.itemsDataList = result.data;



            }, function (result) {
                // $window.alert("Ajax fail");

            });
        };

        //retrieving item categories
        $scope.categoriesSearch = function () {


            $http({
                url: "/sms/saveCategory",
                method: "get"

            }).then(function (result) {

                // $window.alert(result.data);
                $scope.categories = result.data;



            }, function (result) {
                //$window.alert("Ajax fail");

            });


        };
        
        //network clonning
        
        $scope.cloneNetwork=function(){
            
            $http({
                url: "/AMS/cloneNetwork1",
                method: "get"
               
            }).then(function (result) {
                //$window.alert("ajax success" + result.status + " " + result.data);
                bootbox.alert(result.data);
                



            }, function (result) {
                // $window.alert("Ajax fail");

            });
   
        };
        //load finance approval modal

        $scope.financeApproveAsset = function (id, assetName, assetCode, assetDescription, assetValue) {
            document.getElementById("financeAssetName").value = assetName;
            document.getElementById("financeAssetCode").value = assetCode;
            document.getElementById("financeAssetDescription").value = assetDescription;
            document.getElementById("financeAssetValue").value = assetValue;
            document.getElementById("financeAssetId").value = id;



            // alert("finance approval");
            $('#financeApprove').modal();
        };


        //actual finance approval
        $scope.financeAssetApproveEvent = function () {
            var id = document.getElementById("financeAssetId").value;
            var financeComments = document.getElementById("financeComments").value;
            var paymentMode = document.getElementById("modeOfPayment").value;
            // alert(id);exit();

            $http({
                url: "/AMS/approveAsset",
                method: "post",
                params: {
                    "assetId": id,
                    "approvalCategory": 'finance',
                    "financeComments": financeComments,
                    "paymentMode": paymentMode

                }
            }).then(function (result) {
                $('.modal.in').modal('hide');
                bootbox.alert(result.data.message);
                //$window.alert("ajax success" + result.status + " " + result.data);
                //$scope.assetsDataList = result.data;



            }, function (result) {
                // $window.alert("Ajax fail");

            });



        };

        //stores approval modal
        $scope.storesApproveAsset = function (id, assetName, assetCode, assetDescription, assetValue) {
            document.getElementById("storeAssetName").value = assetName;
            document.getElementById("storeAssetCode").value = assetCode;
            document.getElementById("storeAssetDescription").value = assetDescription;
            document.getElementById("storeAssetValue").value = assetValue;
            document.getElementById("storeAssetId").value = id;

            $('#storeApprove').modal();

        };
        //actual stores approval
        $scope.storesAssetApproveEvent = function () {
            var id = document.getElementById("storeAssetId").value;

            var storesComment = document.getElementById("storeComments").value;
            // alert(id);exit();

            $http({
                url: "/AMS/approveAsset",
                method: "post",
                params: {
                    "assetId": id,
                    "approvalCategory": 'stores',
                    "storeComments": storesComment


                }
            }).then(function (result) {
                $('.modal.in').modal('hide');
                bootbox.alert(result.data.message);
                //$window.alert("ajax success" + result.status + " " + result.data);
                //$scope.assetsDataList = result.data;



            }, function (result) {
                // $window.alert("Ajax fail");

            });



        };

        //beneficiary Unit Approval
        $scope.beneficiaryUnitApproveAsset = function (id, assetName, assetCode, assetDescription, assetValue) {

            document.getElementById("buassetName").value = assetName;
            document.getElementById("buassetCode").value = assetCode;
            document.getElementById("buassetDescription").value = assetDescription;
            document.getElementById("buassetValue").value = assetValue;
            document.getElementById("buId").value = id;

            $('#buApprove').modal();

        };
        //actual beneficiary unit approval
        $scope.beneficiaryAssetApproveEvent = function () {
            var id = document.getElementById("buId").value;

            var BUComment = document.getElementById("buComments").value;
            var beneficiary = document.getElementById("buBeneficiaryName").value;
            // alert(id);exit();

            $http({
                url: "/AMS/approveAsset",
                method: "post",
                params: {
                    "assetId": id,
                    "approvalCategory": 'beneficiaryAllocation',
                    "buComments": BUComment,
                    'beneficiary': beneficiary


                }
            }).then(function (result) {
                $('.modal.in').modal('hide');
             bootbox.alert(result.data.message);
                //$window.alert("ajax success" + result.status + " " + result.data);
                //$scope.assetsDataList = result.data;



            }, function (result) {
                // $window.alert("Ajax fail");

            });



        };



        //reject asset approval

        $scope.rejectAsset = function () {
            $('#rejectAsset').modal();

        };



        //searcing and item code
        $scope.itemSearch = function () {

            var itemCode = document.getElementById("checkoutItemcode").value;
            //$window.alert(itemCode);
            $http({
                url: "/sms/inventorySearch",
                method: "get",
                params: {
                    "code": itemCode

                }
            }).then(function (result) {

                //$window.alert(result.data);
                $scope.singleItem = result.data;



            }, function (result) {
                $window.alert("Ajax fail");

            });


        };
        //searching for item to replenish
        $scope.replenishmentItemSearch = function () {

            var itemCode = document.getElementById("Replenishtemcode").value;
            //$window.alert(itemCode);
            $http({
                url: "/sms/inventorySearch",
                method: "get",
                params: {
                    "code": itemCode

                }
            }).then(function (result) {

                //$window.alert(result.data);
                //$scope.itemInfo = result.data;
                $scope.replenishItemName = result.data.itemName;
                $scope.remaining = result.data.remaining;



            }, function (result) {
                $window.alert("Ajax fail");

            });


        };
        //replenishing action
        $scope.inventoryReplenishment = function () {


            var itemCode = document.getElementById("Replenishtemcode").value;
            //alert(itemCode);
            //exit();
            $http({
                url: "/sms/inventoryReplenishment",
                method: "post",
                params: {
                    "code": itemCode,
                    "itemsToAdd": $scope.unitsToAdd

                }
            }).then(function (result) {
                $scope.saveMessage = result.data.message;

                // $window.alert(result.data);
                document.getElementById("Replenishtemcode").value = "";
                document.getElementById("replenishItemName").value = "";
                document.getElementById("runits").value = "";
                document.getElementById("replenishUnits").value = "";





            }, function (result) {
                $window.alert("Ajax fail");

            });


        };
        //replenishing action
        $scope.inventoryCheckout = function () {


            var itemCode = document.getElementById("checkoutItemcode").value;
            //alert(itemCode);
            //exit();
            $http({
                url: "/sms/inventoryCheckout",
                method: "post",
                params: {
                    "code": itemCode,
                    "unitsToCheckout": $scope.unitsToCheckout

                }
            }).then(function (result) {
                $scope.saveMessage = result.data.message;

                // $window.alert(result.data);
                document.getElementById("checkoutItemcode").value = "";
                document.getElementById("checkoutItemName1").value = "";
                document.getElementById("remainingUnits1").value = "";
                document.getElementById("checkoutUnits1").value = "";





            }, function (result) {
                $window.alert("Ajax fail");

            });


        };


        //saving node info
        $scope.saveNodeInfo = function () {
            // alert("we are here");
            $http({
                url: "/AMS/NodeReg10",
                method: "post",
                params: {
                    "nodeName": $scope.nodeName,
                    "nodeIpAddress": $scope.ipaddress,
                    "cloneFromIpAddress": $scope.cloneIP,
                    "numberOfUsers": $scope.numberOfUsers

                }
            }).then(function (result) {
                // $window.alert("ajax success" + result.status + " " + result.data.message);
                $scope.saveNodeMessage = result.data.message;
                document.getElementById("nodeName").value = "";
                document.getElementById("ipaddress").value = "";
                document.getElementById("cloneIP").value = "";
                document.getElementById("numberOfUsers").value = "";
                $('#joinNetwork').modal('hide');
                bootbox.alert("Your Node has been registered successfully");




            }, function (result) {
                // $window.alert("Ajax fail");

            });

        };

        //register Asset
        $scope.registerAsset = function () {
            // alert("we are here");
            $http({
                url: "/AMS/assetController",
                method: "post",
                params: {
                    "assetName": $scope.assetName,
                    "assetCode": $scope.assetCode,
                    "assetValue": $scope.assetValue,
                    "assetDescription": $scope.assetDescription


                }
            }).then(function (result) {
                //$window.alert("ajax success" + result.status + " " + result.data.message);
                // alert("asset has been added");
                $scope.saveAssetMessage = result.data.message;
                document.getElementById("assetName").value = "";
                document.getElementById("assetCode").value = "";
                document.getElementById("assetValue").value = "";
                document.getElementById("assetDescription").value = "";
                var data=result.data;
                $('#registerAsset').modal('hide');
                bootbox.alert(data.message);





            }, function (result) {
                // $window.alert("Ajax fail");

            });

        };



        $scope.assetsList = function () {
            //  alert("test");
            // window.alert("test");
            $http({
                url: "/AMS/assetsList",
                method: "get",
                params: {
                    "assetCategory": "APPROVED"

                }
            }).then(function (result) {
                //$window.alert("ajax success" + result.status + " " + result.data);
                $scope.assetsDataList = result.data;
//                        $('#').DataTable({
//                    dom: 'Bfrtip',
//                    buttons: [
//                        'copyHtml5',
//                        'excelHtml5',
//                        'csvHtml5',
//                        'pdfHtml5'
//                    ]
//                });



            }, function (result) {
                // $window.alert("Ajax fail");

            });
        };




        $scope.assetValidation = function (id) {
            //  bootbox.alert("testing the waters"+id);exit();


            $http({
                url: "/AMS/validateAsset",
                method: "post",
                params: {
                    "assetId": id

                }
            }).then(function (result) {
                //$window.alert("ajax success" + result.status + " " + result.data);exit();
                var data = result.data;
                bootbox.alert(data.message);
//                        $('#').DataTable({
//                    dom: 'Bfrtip',
//                    buttons: [
//                        'copyHtml5',
//                        'excelHtml5',
//                        'csvHtml5',
//                        'pdfHtml5'
//                    ]
//                });



            }, function (result) {
                // $window.alert("Ajax fail");

            });
        };


        //save profile
        $scope.createProfile = function () {
            // alert("we are here");
            $http({
                url: "/AMS/createProfile",
                method: "post",
                params: {
                    "firstname": $scope.firstname,
                    "lastname": $scope.lastname,
                    "username": $scope.username,
                    "password": $scope.password

                }
            }).then(function (result) {
                // $window.alert("ajax success" + result.status + " " + result.data.message);
                 var data = result.data;
                bootbox.alert(data.message);
                $scope.saveProfileMessage = result.data.message;
                document.getElementById("firstname").value = "";
                document.getElementById("lastname").value = "";
                document.getElementById("username").value = "";
                document.getElementById("password").value = "";
                document.getElementById("cpassword").value = "";




            }, function (result) {
                // $window.alert("Ajax fail");

            });

        };


        $scope.nodeInfo = function () {
            //alert("test");
            // window.alert("test");
            $http({
                url: "/AMS/nodeInfo",
                method: "get"
//                    params: {
//                        "itemCode": $scope.itemCode,
//                        "itemName": $scope.itemName,
//                        "reorderPoint": $scope.reorderPoint
//                    }
            }).then(function (result) {
                // $window.alert("ajax success" + result.status + " " +JSON.stringify(result.data));
                var info = result.data;
                // alert(info.nodeName);
                document.getElementById("nodeName").value = info.nodeName;
                document.getElementById("ipaddress").value = info.ipAddress;
                document.getElementById("numberOfUsers").value = info.numberOfUsers;
                $scope.nodeInformation = result.data;



            }, function (result) {
                // $window.alert("Ajax fail");

            });
        };


        $scope.unapproveAssetsList = function () {
            //  alert("test");
            // window.alert("test");
            $http({
                url: "/AMS/assetsList",
                method: "get",
                params: {
                    "assetCategory": "UNAPPROVED"

                }
            }).then(function (result) {
                //$window.alert("ajax success" + result.status + " " + result.data);
                $scope.assetsDataList = result.data;



            }, function (result) {
                // $window.alert("Ajax fail");

            });
        };


        $scope.approveAsset = function (id) {


            $http({
                url: "/AMS/approveAsset",
                method: "post",
                params: {
                    "assetId": id

                }
            }).then(function (result) {
                //$window.alert("ajax success" + result.status + " " + result.data);
                //$scope.assetsDataList = result.data;



            }, function (result) {
                // $window.alert("Ajax fail");

            });
        };

        $scope.financeUnitRejectAsset = function (id, assetName, assetCode, assetDescription, assetValue) {

            document.getElementById("financeRejectassetCode").value = assetCode;
            document.getElementById("financeRejectassetName").value = assetName;
            document.getElementById("financeRejectassetValue").value = assetValue;
            document.getElementById("financeRejectassetDescription").value = assetDescription;
            document.getElementById("financeRejectAssetId").value = id;

            $('#financeRejectAsset').modal();

        };

        //actual finance approval
        $scope.financeAssetRejectEvent = function () {
            var id = document.getElementById("financeRejectAssetId").value;
            var reasonForRejection = document.getElementById("financeRejectionReasons").value;

            // alert(id);exit();

            $http({
                url: "/AMS/assetRejection",
                method: "post",
                params: {
                    "assetId": id,
                    "reasonForRejection": reasonForRejection,
                    'type': 'FINANCEREJECTION'


                }
            }).then(function (result) {
                $('.modal.in').modal('hide');
                bootbox.alert("This Asset has been rejected.It has been returned to Stores Department");




            }, function (result) {
                // $window.alert("Ajax fail");

            });



        };


        $scope.storesUnitRejectAsset = function (id, assetName, assetCode, assetDescription, assetValue) {

            document.getElementById("storesRejectassetCode").value = assetCode;
            document.getElementById("storesRejectassetName").value = assetName;
            document.getElementById("storesRejectassetValue").value = assetValue;
            document.getElementById("storesRejectassetDescription").value = assetDescription;
            document.getElementById("storesRejectAssetId").value = id;

            $('#storesRejectAsset').modal();

        };
        
        
              //actual stores rejection
        $scope.storesAssetRejectEvent = function () {
            var id = document.getElementById("storesRejectAssetId").value;
            var reasonForRejection = document.getElementById("storesRejectionReasons").value;

            // alert(id);exit();

            $http({
                url: "/AMS/assetRejection",
                method: "post",
                params: {
                    "assetId": id,
                    "reasonForRejection": reasonForRejection,
                    'type': 'STORESREJECTION'


                }
            }).then(function (result) {
                $('.modal.in').modal('hide');
                bootbox.alert("This Asset has been rejected.It will never appear on the blockchain network.");




            }, function (result) {
                // $window.alert("Ajax fail");

            });



        };
        //BENEFICIARYREJECTION
              $scope.buRejectAsset = function (id, assetName, assetCode, assetDescription, assetValue) {

            document.getElementById("buRejectassetCode").value = assetCode;
            document.getElementById("buRejectassetName").value = assetName;
            document.getElementById("buRejectassetValue").value = assetValue;
            document.getElementById("buRejectassetDescription").value = assetDescription;
            document.getElementById("buRejectAssetId").value = id;

            $('#buRejectAsset').modal();

        };
        
        
                //actual stores rejection
        $scope.buAssetRejectEvent = function () {
            var id = document.getElementById("buRejectAssetId").value;
            var reasonForRejection = document.getElementById("buRejectionReasons").value;

            // alert(id);exit();

            $http({
                url: "/AMS/assetRejection",
                method: "post",
                params: {
                    "assetId": id,
                    "reasonForRejection": reasonForRejection,
                    'type': 'BENEFICIARYREJECTION'


                }
            }).then(function (result) {
                $('.modal.in').modal('hide');
                bootbox.alert("This Asset has been rejected.It will never appear on the blockchain network.");




            }, function (result) {
                // $window.alert("Ajax fail");

            });



        };







    }]);

