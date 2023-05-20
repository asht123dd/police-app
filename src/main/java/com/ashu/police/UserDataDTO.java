package com.ashu.police;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
//import murraco.model.AppUserRole;

@Data
@NoArgsConstructor
public class UserDataDTO {
	 private String username;
//	  @ApiModelProperty(position = 1)
	  private String email;
//	  @ApiModelProperty(position = 2)
	  private String password;
//	  @ApiModelProperty(position = 3)
	  List<AppUserRole> appUserRoles;
}
