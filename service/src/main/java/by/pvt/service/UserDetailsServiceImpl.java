package by.pvt.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import by.pvt.repository.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User byLogin = userService.getByLogin(username);
		if (byLogin == null) {
			throw new UsernameNotFoundException("No. Just no.");
		}
		return new org.springframework.security.core.userdetails.User(byLogin.getLogin(), byLogin.getPassword(),
				getAuthorities(byLogin));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(User byLogin) {
		ArrayList<GrantedAuthority> arrayList = new ArrayList<>();
		arrayList.add(new SimpleGrantedAuthority(byLogin.getRole().name()));
		return arrayList;
	}

}
