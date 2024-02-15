package com.songdiary.SongDiary.user.dto;

import lombok.Data;

@Data
public class UserInfoResponse {
  private String profileId;
  private String email;
  private String name;
  private String sex;
}
