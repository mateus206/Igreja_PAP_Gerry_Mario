package com.example.igrejas.models.utils;

// Comentários adicionados como aluno para explicar melhor o código.

// deixei aqui os links da API para não repetir em todas as classes
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
    public static final String NOTIFICACOES_URL = BASE_URL + "/api/notificacoes";
    public static final String CONTRIBUICOES_URL = BASE_URL + "/api/contribuicoes";
    public static final String MINISTERIOS_INSCRICAO_URL = BASE_URL + "/api/ministerios/inscricao";

    private ApiConfig() {

    }
}
