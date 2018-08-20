
package com.ams.beans;

import com.ams.models.DappUser;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class UserBean implements UserI  {
     @PersistenceContext(unitName = "APP_PU")
    private EntityManager em;

    @Override
    public DappUser login(String username,String password) {
        
       DappUser user = null;
        List<DappUser> loggingUser = em.createQuery("SELECT u FROM DappUser u WHERE u.userName = :username AND u.password = :password").setParameter("username", username).setParameter("password", password).getResultList();
        if (!loggingUser.isEmpty()) {
            user = loggingUser.get(0);
            user.setValid(true);

        }
        return user;

    }
    

    @Override
    public DappUser saveUser(DappUser user) {
        return em.merge(user);
    }

    @Override
    public DappUser findUserByUsername(String username) {
              DappUser user = null;
        List<DappUser> loggingUser = em.createQuery("SELECT u FROM DappUser u WHERE u.userName = :username").setParameter("username", username).getResultList();
        if (!loggingUser.isEmpty()) {
            user = loggingUser.get(0);
            user.setValid(true);

        }
        return user;

        
        
    }
        
    

    
}
