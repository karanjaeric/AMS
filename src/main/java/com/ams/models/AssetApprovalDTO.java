
package com.ams.models;

/**
 *
 * @author ekaranja
 */
public class AssetApprovalDTO {
    
    //dto properties
    private String assetCode;
    private String approvingNode;
    private String approvingUser;
    private String approvalStatus;
    private YesNo financeApproved;
    private YesNo buApproved;
    private YesNo storesApproved;

    public YesNo getFinanceApproved() {
        return financeApproved;
    }

    public void setFinanceApproved(YesNo financeApproved) {
        this.financeApproved = financeApproved;
    }

    public YesNo getBuApproved() {
        return buApproved;
    }

    public void setBuApproved(YesNo buApproved) {
        this.buApproved = buApproved;
    }

    public YesNo getStoresApproved() {
        return storesApproved;
    }

    public void setStoresApproved(YesNo storesApproved) {
        this.storesApproved = storesApproved;
    }
    
    
    
    
    //getters and setters

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getApprovingNode() {
        return approvingNode;
    }

    public void setApprovingNode(String approvingNode) {
        this.approvingNode = approvingNode;
    }

    public String getApprovingUser() {
        return approvingUser;
    }

    public void setApprovingUser(String approvingUser) {
        this.approvingUser = approvingUser;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
    
    
    
    
}
