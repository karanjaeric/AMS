/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ams.beans;

import com.ams.models.DappUser;
import javax.ejb.Local;

/**
 *
 * @author ekaranja
 */
@Local
public interface UserI {
    DappUser login(String username,String password);    
    DappUser saveUser(DappUser user);
    DappUser findUserByUsername(String username);
    
}
