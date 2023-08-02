package won.shop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import won.shop.Service.MemberService;
import won.shop.constant.Gender;
import won.shop.domain.Member;
import won.shop.dto.MemberFormDto;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder PasswordEncoder;

    @ModelAttribute("gender")
    public Gender[] GetGender() {
        return Gender.values();
    }

    @GetMapping("/new")
    public String memberForm(@ModelAttribute MemberFormDto memberFormDto){
       // model.addAttribute("memberFormDto",new MemberFormDto());
        return  "/member/memberForm";
    }
    @PostMapping("/new")
    public String memberFromSave(@Validated MemberFormDto memberFormDto,
                                 BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "/member/memberForm";
        }
        try{
            Member newMember = Member.CreateMember(memberFormDto, PasswordEncoder);
            memberService.saveMember(newMember);
        }catch (Exception e){
            model.addAttribute("errorMessage",e.getMessage());
            return "/member/memberForm";
        }

        return  "redirect:/"; // 회원 가입 후 메인 화면으로 이동
    }
}
