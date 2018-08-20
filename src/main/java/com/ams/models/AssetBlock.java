package com.ams.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author ekaranja
 */
@Entity
@Table(name = "assets")
public class AssetBlock implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int hashCode;
    private int previousHashCode;
    private String blockOwner;
    private String assetName;
    private String assetCode;
    private String assetDescription;
    private Double assetValue;
    private String financeComments;
    private String paymentMode;
    @Enumerated(EnumType.STRING)
    private AssetRegStatus regStatus;

    private String creatingNode;
    private String approvingNode;
    private String storesComment;
    private String beneficiaryAllocationComments;
    private String beneficiary;
    @Enumerated(EnumType.STRING)
    private YesNo financeApproval;

    @Enumerated(EnumType.STRING)
    private YesNo storesApproval;

    @Enumerated(EnumType.STRING)
    private YesNo beneficiaryUnitApproval;

    @Enumerated(EnumType.STRING)
    private YesNo financeRejection;

    @Enumerated(EnumType.STRING)
    private YesNo storesRejection;

    private String storesRejectionComments;
    
     @Enumerated(EnumType.STRING)
    private YesNo beneficiaryUnitRejection;

    private String beneficiaryUnitRejectionComments;
    
    private YesNo valid;

    public YesNo getValid() {
        return valid;
    }

    public void setValid(YesNo valid) {
        this.valid = valid;
    }
    

    public YesNo getBeneficiaryUnitRejection() {
        return beneficiaryUnitRejection;
    }

    public void setBeneficiaryUnitRejection(YesNo beneficiaryUnitRejection) {
        this.beneficiaryUnitRejection = beneficiaryUnitRejection;
    }

    public String getBeneficiaryUnitRejectionComments() {
        return beneficiaryUnitRejectionComments;
    }

    public void setBeneficiaryUnitRejectionComments(String beneficiaryUnitRejectionComments) {
        this.beneficiaryUnitRejectionComments = beneficiaryUnitRejectionComments;
    }
    
    

    public YesNo getStoresRejection() {
        return storesRejection;
    }

    public void setStoresRejection(YesNo storesRejection) {
        this.storesRejection = storesRejection;
    }

    public String getStoresRejectionComments() {
        return storesRejectionComments;
    }

    public void setStoresRejectionComments(String storesRejectionComments) {
        this.storesRejectionComments = storesRejectionComments;
    }

    @Enumerated(EnumType.STRING)
    private YesNo isRejected;

    public YesNo getIsRejected() {
        return isRejected;
    }

    public void setIsRejected(YesNo isRejected) {
        this.isRejected = isRejected;
    }

    private String financeRejectionComments;

    public YesNo getFinanceRejection() {
        return financeRejection;
    }

    public void setFinanceRejection(YesNo financeRejection) {
        this.financeRejection = financeRejection;
    }

    public String getFinanceRejectionComments() {
        return financeRejectionComments;
    }

    public void setFinanceRejectionComments(String financeRejectionComments) {
        this.financeRejectionComments = financeRejectionComments;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getFinanceComments() {
        return financeComments;
    }

    public void setFinanceComments(String financeComments) {
        this.financeComments = financeComments;
    }

    public String getBeneficiary() {
        return beneficiary;
    }

    public void setBeneficiary(String beneficiary) {
        this.beneficiary = beneficiary;
    }

    public String getBeneficiaryAllocationComments() {
        return beneficiaryAllocationComments;
    }

    public void setBeneficiaryAllocationComments(String beneficiaryAllocationComments) {
        this.beneficiaryAllocationComments = beneficiaryAllocationComments;
    }

    public String getStoresComment() {
        return storesComment;
    }

    public void setStoresComment(String storesComment) {
        this.storesComment = storesComment;
    }

    public YesNo getFinanceApproval() {
        return financeApproval;
    }

    public void setFinanceApproval(YesNo financeApproval) {
        this.financeApproval = financeApproval;
    }

    public YesNo getStoresApproval() {
        return storesApproval;
    }

    public void setStoresApproval(YesNo storesApproval) {
        this.storesApproval = storesApproval;
    }

    public YesNo getBeneficiaryUnitApproval() {
        return beneficiaryUnitApproval;
    }

    public void setBeneficiaryUnitApproval(YesNo beneficiaryUnitApproval) {
        this.beneficiaryUnitApproval = beneficiaryUnitApproval;
    }

    public String getCreatingNode() {
        return creatingNode;
    }

    public void setCreatingNode(String creatingNode) {
        this.creatingNode = creatingNode;
    }

    public String getApprovingNode() {
        return approvingNode;
    }

    public void setApprovingNode(String approvingNode) {
        this.approvingNode = approvingNode;
    }

    public AssetRegStatus getRegStatus() {
        return regStatus;
    }

    public void setRegStatus(AssetRegStatus regStatus) {
        this.regStatus = regStatus;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public String getAssetDescription() {
        return assetDescription;
    }

    public void setAssetDescription(String assetDescription) {
        this.assetDescription = assetDescription;
    }

    public Double getAssetValue() {
        return assetValue;
    }

    public void setAssetValue(Double assetValue) {
        this.assetValue = assetValue;
    }

    public String getAssetName() {
        return assetName;
    }

    public void setAssetName(String assetName) {
        this.assetName = assetName;
    }

    //location
    //tagno
    public int getHashCode() {
        return hashCode;
    }

    public void setHashCode(int hashCode) {
        this.hashCode = hashCode;
    }

    public int getPreviousHashCode() {
        return previousHashCode;
    }

    public void setPreviousHashCode(int previousHashCode) {
        this.previousHashCode = previousHashCode;
    }

    public String getBlockOwner() {
        return blockOwner;
    }

    public void setBlockOwner(String blockOwner) {
        this.blockOwner = blockOwner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AssetBlock)) {
            return false;
        }
        AssetBlock other = (AssetBlock) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.ams.models.Block[ id=" + id + " ]";
    }

}
