/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ams.beans;

import com.ams.models.AssetBlock;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ekaranja
 */
@Stateless
public class ClonnedDataBean implements ClonnedDataI{
        @PersistenceContext(unitName = "APP_PU")
    private EntityManager em;

    @Override
    public void saveClonnedAsset(AssetBlock assetBlock) {
        em.merge(assetBlock);
        
    }
    
}
