package com.nerds.stuppingmall.dto;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.service.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MemberDto implements UserDetails, Serializable {
	private static final long serialVersionUID = 174726374856727L;	// 이건 뭐지?
	
	private String _id;
	private Role role;
	private String userId;
	private String password;
	private String name;
	private String email;
	private String phoneNum;
	private String birth;
	private boolean man;
	private Collection<GrantedAuthority> authorities;
	
	public Member toDomain() {
		return new Member(_id, role.name(), userId, password, name, email, phoneNum, birth, man);
	}

	public MemberDto(Member member) {
		this._id = member.get_id();
		this.userId = member.getUserId();
		this.password = member.getPassword();
		this.name = member.getName();
		this.email = member.getEmail();
		this.phoneNum = member.getPhoneNum();
		this.birth = member.getBirth();
		this.man = member.isMan();
		this.role = Role.valueOf(member.getRole());
	}
	
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
}
