package com.asraf.infosapp.model;

import java.util.Collection;
import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.asraf.infosapp.optionenum.Gender;
import com.asraf.infosapp.optionenum.MaritialStatus;
import com.asraf.infosapp.optionenum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="_user")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotEmpty
	@Size(min = 3,message = "min character should be 3")
	@Pattern(regexp="[a-z]{3,12}", message = "First Name must not contains numbers or special characters")
	private String firstName;

	@NotEmpty
	@Size(min = 3,message = "min character should be 3")
	@Pattern(regexp="[a-z]{3,12}", message = "Last Name must not contains numbers or special characters")
	private String lastName;

	@NotEmpty
	@Column(unique=true)
	@Size(min = 5,message = "min character should be 3")
	@Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$",message = "email should not contain special character but valid email style will be fine")
	private String email;

	@NotEmpty
	@Column(unique=true)
	@Size(min=10,max=10,message="mobile number should be 10 charecter")
	@Pattern(regexp ="(0/91)?[7-9][0-9]{9}",message="Enter valid Mobile Number")
	private String mobile;


	private Gender gender ;

	private MaritialStatus maritialStatus;

	@NotEmpty
	//	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}",message="a digit, a lower case letter,an upper case letter,a special character and must occur at least once and no whitespace allowed in the entire string, 8 character minimum")
	private String password;

	@Enumerated(EnumType.STRING)
	private Role role;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
