package com.sb02.springsecurityprac.controller;

import com.sb02.springsecurityprac.dto.CreateRequest;
import com.sb02.springsecurityprac.dto.CreateResult;
import com.sb02.springsecurityprac.dto.PublicInfo;
import com.sb02.springsecurityprac.dto.UserBoardInfo;
import com.sb02.springsecurityprac.dto.UserList;
import com.sb02.springsecurityprac.dto.UserRole;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class BoardController {
    // 누구나 접근 가능
    @GetMapping("/public/info")
    public ResponseEntity<PublicInfo> publicInfo(){
        PublicInfo publicInfo = new PublicInfo("This is public information", "success");
        return ResponseEntity.ok(publicInfo);
    }

    // 직원만 접근 가능
    @GetMapping("/board")
    public ResponseEntity<UserBoardInfo> boardList(Authentication auth){
        return ResponseEntity.ok(
            new UserBoardInfo(
                auth.getName(),
                new ArrayList<>(auth.getAuthorities()),
                List.of("게시글 1", "게시글 2", "게시글 3")
            )
        );
    }

    // 직원만 접근 가능
    @PostMapping("/board")
    public ResponseEntity<CreateResult> craeteBoard(@RequestBody CreateRequest request){
        return ResponseEntity.ok(
            new CreateResult(
                "게시글이 성공적으로 생성되었습니다.",
                request.title()
            )
        );
    }

    // 관리자만 접근 가능
    @GetMapping("/admin/users")
    public ResponseEntity<UserList> userList(){
        return ResponseEntity.ok(
            new UserList(
                List.of(
                    new UserRole("employee", "USER"),
                    new UserRole("adim", "ADMIN")
                )
            )
        );
    }
}
