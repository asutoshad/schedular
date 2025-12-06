package com.schedular.service.impl;

import com.schedular.model.OAuthUser;
import com.schedular.repository.OAuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private OAuthUserRepository oauthUserRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauthUser = super.loadUser(userRequest);

        String email = oauthUser.getAttribute("email");
        String name = oauthUser.getAttribute("name");
        String provider = userRequest.getClientRegistration().getRegistrationId().toUpperCase();

        oauthUserRepository.findByEmail(email).orElseGet(() -> {
            OAuthUser user = new OAuthUser();
            user.setEmail(email);
            user.setName(name);
            user.setProvider(provider);
            return oauthUserRepository.save(user);
        });

        return oauthUser;
    }
}
