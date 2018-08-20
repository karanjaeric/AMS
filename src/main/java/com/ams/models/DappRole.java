/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ams.models;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author ekaranja
 */
@Entity
public class DappRole implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name = "roleName")
    private String roleName;
    
    @Enumerated(EnumType.STRING)
    private YesNo accessFinanceDept;
    
    @Enumerated(EnumType.STRING)
    private YesNo accessStoresDept;
    
    @Enumerated(EnumType.STRING)
    private YesNo beneficiaryUnitDept;
    
   @Enumerated(EnumType.STRING)
    private YesNo staff;

    public YesNo getStaff() {
        return staff;
    }

    public void setStaff(YesNo staff) {
        this.staff = staff;
    }
   
   

    private String roleDescription;

    public YesNo getAccessFinanceDept() {
        return accessFinanceDept;
    }

    public void setAccessFinanceDept(YesNo accessFinanceDept) {
        this.accessFinanceDept = accessFinanceDept;
    }

    public YesNo getAccessStoresDept() {
        return accessStoresDept;
    }

    public void setAccessStoresDept(YesNo accessStoresDept) {
        this.accessStoresDept = accessStoresDept;
    }

    public YesNo getBeneficiaryUnitDept() {
        return beneficiaryUnitDept;
    }

    public void setBeneficiaryUnitDept(YesNo beneficiaryUnitDept) {
        this.beneficiaryUnitDept = beneficiaryUnitDept;
    }

    

  
    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
        if (!(object instanceof DappRole)) {
            return false;
        }
        DappRole other = (DappRole) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ams.models.DappRole[ id=" + id + " ]";
    }

}
