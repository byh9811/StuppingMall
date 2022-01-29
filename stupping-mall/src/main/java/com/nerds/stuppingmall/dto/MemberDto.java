package com.nerds.stuppingmall.dto;

<<<<<<< Updated upstream
=======
import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nerds.stuppingmall.domain.AuthRequest;
>>>>>>> Stashed changes
import com.nerds.stuppingmall.domain.Member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDto {
	private String _id;
	private String userId;
	private String password;
	private String name;
	private String email;
	private String phoneNum;
	private String birth;
	private boolean man;
	
	public Member toDomain() {
		return new Member(_id, userId, password, name, email, phoneNum, birth, man);
	}
<<<<<<< Updated upstream
=======
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { return authorities; }

	@Override
	public String getUsername() { return userId; }

	@Override
	public boolean isAccountNonExpired() { return true; }

	@Override
	public boolean isAccountNonLocked() { return true; }

	@Override
	public boolean isCredentialsNonExpired() { return true; }

	@Override
	public boolean isEnabled() { return true; }
	
	public AuthRequest toAuthRequest() {
		return new AuthRequest(userId, password, role);
	}
>>>>>>> Stashed changes
}
