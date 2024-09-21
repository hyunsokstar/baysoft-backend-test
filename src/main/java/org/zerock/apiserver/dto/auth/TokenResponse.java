package org.zerock.apiserver.dto.auth;

public class TokenResponse {
    private String accessToken;

    // 기본 생성자
    public TokenResponse() {}

    // 모든 필드를 포함한 생성자
    public TokenResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    // Getter와 Setter
    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}