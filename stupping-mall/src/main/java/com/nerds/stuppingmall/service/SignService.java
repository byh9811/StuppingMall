package com.nerds.stuppingmall.service;

import org.springframework.stereotype.Service;

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
                .orElseThrow(CEmailLoginFailedException::new);

        // 회원 패스워드 일치 여부 확인
        if (!passwordEncoder.matches(userLoginRequestDto.getPassword(), member.getPassword()))
            throw new CEmailLoginFailedException();

        // AccessToken, RefreshToken 발급
        TokenDto tokenDto = jwtProvider.createTokenDto(member.getUserId(), member.getRoles());

        // RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .key(member.getUserId())
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
