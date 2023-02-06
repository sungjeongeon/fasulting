package com.fasulting.entity.user;

import com.fasulting.entity.BaseEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@DynamicInsert // Apply changed fields only
@DynamicUpdate // Apply changed fields only
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table (name = "user")
public class UserEntity extends BaseEntity {

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private Long seq;

	@OneToOne(mappedBy = "user")
	private RoleEntity role;

   	@Column(name = "email")
	private String email;

   	@Column(name = "password")
	private String password;

   	@Column(name = "birth")
	private String birth;

   	@Column(name = "number")
	private String number;

//   	@Column(name = "nation")
//	private String nation;
//
//   	@Column(name = "nation_code")
//	private String nationCode;

   	@Column(name = "name")
	private String name;

   	@Column(name = "del_date")
	private LocalDateTime delDate;

   	@Column(name = "del_by")
	private String delBy;

   	@Column(name = "del_yn")
	private String delYn;

	@Builder
	public UserEntity(String email, String password, String birth, String number, String name) {
		this.email = email;
		this.password = password;
		this.birth = birth;
		this.number = number;
		this.name = name;
		this.delYn = "N";
	}

	public void updateUser(String name, String number) {
		this.name = name;
		this.number = number;
	}

	public void updateByWithdrawal(String delYn, String delBy, LocalDateTime delDate) {
		this.delYn = delYn;
		this.delBy = delBy;
		this.delDate = delDate;
	}

	public void resetPassword(String password) {
		this.password = password;
	}
}