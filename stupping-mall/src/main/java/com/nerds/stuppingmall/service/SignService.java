package com.nerds.stuppingmall.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nerds.stuppingmall.domain.Member;
import com.nerds.stuppingmall.dto.TokenDto;
import com.nerds.stuppingmall.provider.JwtProvider;
import com.nerds.stuppingmall.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignService {
	private final MemberRepository memberRepository;
	private final TokenRepository tokenRepository;
	private final JwtProvider jwtProvider;
	private final PasswordEncoder passwordEncoder;
	
    @Transactional
    public TokenDto login(MemberLoginRequestDto userLoginRequestDto) {

        // 회원 정보 존재하는지 확인
        Member member = memberRepository.findById(userLoginRequestDto.getId)
                .orElseThrow(RuntimeException::new);

        // 회원 패스워드 일치 여부 확인
        if (!passwordEncoder.matches(userLoginRequestDto.getPassword(), member.getPassword()))
            throw new RuntimeException();

        // AccessToken, RefreshToken 발급
        TokenDto tokenDto = jwtProvider.createTokenDto(member.get_id(), member.getRoles());

        // RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(member.get_id())
                .token(tokenDto.getRefreshToken())
                .build();
        tokenRepository.save(refreshToken);
        return tokenDto;
    }

    @Transactional
    public Long signup(UserSignupRequestDto userSignupDto) {
        if (memberRepository.findById(userSignupDto.getId()).isPresent())
            throw new CEmailSignupFailedException();
        return memberRepository.save(userSignupDto.toEntity(passwordEncoder)).getUserId();
    }
}
