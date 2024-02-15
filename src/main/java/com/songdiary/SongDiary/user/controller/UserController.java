package com.songdiary.SongDiary.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.server.ResponseStatusException;

import com.songdiary.SongDiary.user.domain.User;
import com.songdiary.SongDiary.user.dto.UserInfoRequest;
import com.songdiary.SongDiary.user.dto.UserInfoResponse;
import com.songdiary.SongDiary.user.dto.UserJoinRequest;
import com.songdiary.SongDiary.user.dto.UserLoginRequest;
import com.songdiary.SongDiary.user.dto.UserNewPasswordRequest;
import com.songdiary.SongDiary.user.service.UserService;


@RestController
@RequestMapping("/user")
public class UserController {
  
  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  // 회원가입
  @PostMapping("join")
  public ResponseEntity<?> userSignUp(@RequestBody UserJoinRequest req) {
    try {
      User registeredUser = userService.join(req);
      return new ResponseEntity<>(registeredUser.getUserName()+"("+registeredUser.getUserProfileId()+") 님 계정이 생성되었습니다.", HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }

  // 로그인
  @PostMapping("login")
  public ResponseEntity<?> userLogin(@RequestBody UserLoginRequest req) {
    try {
      userService.login(req);
      return new ResponseEntity<>(HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
  }
  
  // 로그아웃
  @PostMapping("logout")
  public ResponseEntity<?> userLogout(@SessionAttribute(name="userId", required=false) Long userId) {
      //TODO: process POST request
      
      return new ResponseEntity<>(HttpStatus.OK);
  }
  
  // 회원탈퇴
  @DeleteMapping("delete")
  public ResponseEntity<?> userDelete(/*@SessionAttribute(name="userId", required=false)*/ Long userId) {
    if (userId != null) {
      userService.delete(userId);
      return new ResponseEntity<>("회원탈퇴가 완료되었습니다.", HttpStatus.OK);
    }
    else {
      return new ResponseEntity<>("로그인 후 이용해주세요.", HttpStatus.UNAUTHORIZED);
    }
  }

  // 회원정보 조회
  @GetMapping("info/check")
  public UserInfoResponse userInfo(/*@SessionAttribute(name="userId", required=false)*/ Long userId) {
    if (userId != null) {
      return userService.userInfo(userId);
    }
    else {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 후 이용해주세요.");
    }
  }
  
  // 회원정보 수정
  @PostMapping("info/edit-profile")
  public ResponseEntity<?> userInfoEdit(/*@SessionAttribute(name="userId", required=false)*/ Long userId, @RequestBody UserInfoRequest req) {
    if (userId != null) {
      try {
        userService.editUserInfo(userId, req);
        return new ResponseEntity<>("프로필이 업데이트되었습니다.", HttpStatus.OK);
      }
      catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
      }
    }
    else {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 후 이용해주세요.");
    }
  }

  // 비밀번호 변경
  @PostMapping("info/edit-password")
  public ResponseEntity<?> postMethodName(/*@SessionAttribute(name="userId", required=false)*/ Long userId, @RequestBody UserNewPasswordRequest req) {
    if (userId != null) {
      try {
        userService.editPassword(userId, req);
        return new ResponseEntity<>("비밀번호가 변경되었습니다.", HttpStatus.OK);
      }
      catch (Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
      }
    }
    else {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인 후 이용해주세요.");
    }
  }
  
}
