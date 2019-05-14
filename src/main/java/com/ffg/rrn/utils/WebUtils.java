/**
 * 
 */
package com.ffg.rrn.utils;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * @author FFGRRNTeam
 *
 */
public class WebUtils {
	 
    public static String toString(User user) {
        StringBuilder sb = new StringBuilder();
 
        sb.append(user.getUsername());
 
        Collection<GrantedAuthority> authorities = user.getAuthorities();
        if (authorities != null && !authorities.isEmpty()) {
            sb.append(" (");
            boolean first = true;
            for (GrantedAuthority a : authorities) {
                if (first) {
                    sb.append(a.getAuthority());
                    first = false;
                } else {
                    sb.append(", ").append(a.getAuthority());
                }
            }
            sb.append(")");
        }
        return sb.toString();
    }

	public static String grantOnProperty(User user) {

		Collection<GrantedAuthority> authorities = user.getAuthorities();
		if (authorities != null && !authorities.isEmpty()) {

			for (GrantedAuthority a : authorities) {

				if (!("ROLE_USER".equals(a.getAuthority()) || "ROLE_ADMIN".equals(a.getAuthority()))) {
					if (a.getAuthority() != null) {
						return a.getAuthority();
					}
				}
			}
		}
		return "";
	}

	public static boolean isAdmin(User user) {

		Collection<GrantedAuthority> authorities = user.getAuthorities();
		if (authorities != null && !authorities.isEmpty()) {

			for (GrantedAuthority a : authorities) {
				if ("ROLE_ADMIN".equals(a.getAuthority())) {
					return true;
				}
			}
		}
		return false;
	}

}