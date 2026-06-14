package com.example.igrejas.models.utils;

public class ApiConfig {
    public static final String BASE_URL = "https://crescent-faster-apron.ngrok-free.dev";

    public static final String SIGNUPURL = BASE_URL + "/api/signup";
    public static final String LOGINURL = BASE_URL + "/api/login";
    public static final String EVENTOSURL = BASE_URL + "/api/Eventos";
    public static final String ACAO_SOLIDARIAS_URL = BASE_URL + "/api/acao-solidarias";
    public static final String PEDIDO_ORACOES_URL = BASE_URL + "/api/pedido-oracoes";
    public static final String APOIO_SOCIAIS_URL = BASE_URL + "/api/apoio-sociais";
    public static final String PROFILE_URL = BASE_URL + "/api/users/profile";
    public static final String UPDATE_PROFILE_URL = BASE_URL + "/api/users/profile";
    public static final String UPDATE_PASSWORD_URL = BASE_URL + "/api/users/profile/password";

    private ApiConfig() {

    }
}
