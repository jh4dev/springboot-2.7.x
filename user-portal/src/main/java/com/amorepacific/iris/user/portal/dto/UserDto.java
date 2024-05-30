package com.amorepacific.iris.user.portal.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {
	
	/* 사용자 아이디 */
	private String userId;
	
	/* 사용자 패스워드 */
	private String userPw;
	
	/* 사용자 명 */
	private String userName;
	
	/* 사용자 권한 코드 */
	private String userRoleCd;
	
	/* 사용자 권한 명 */
	private String userRoleNm;
	
	/* 사용자 소속 코드*/
	private String userTeamCd;
	
	/* 사용자 소속 명 */
	private String userTeamNm;
	

	@Builder
	UserDto(String userId, String userPw, String userName, String userRoleCd, String userRoleNm, String userTeamCd, String userTeamNm) {
		this.userId 	= userId;
		this.userPw 	= userPw;
		this.userName 	= userName;
		this.userRoleCd	= userRoleCd;
		this.userRoleNm	= userRoleNm;
		this.userTeamCd	= userTeamCd;
		this.userTeamNm	= userTeamNm;
	}
}
