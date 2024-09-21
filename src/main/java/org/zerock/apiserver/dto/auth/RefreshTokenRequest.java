package org.zerock.apiserver.dto.auth;

public class RefreshTokenRequest {
    private String refreshToken;

    // 기본 생성자
    public RefreshTokenRequest() {}

    // 모든 필드를 포함한 생성자
    public RefreshTokenRequest(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    // Getter와 Setter
    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}