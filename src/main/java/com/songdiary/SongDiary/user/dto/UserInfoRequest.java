package com.songdiary.SongDiary.user.dto;

import lombok.Data;

@Data
public class UserInfoRequest {
  private String email;
  private String name;
  private String sex;
}
