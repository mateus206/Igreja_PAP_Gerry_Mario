package com.example.igrejas.models.signupresponse;

// Comentários adicionados como aluno para explicar melhor o código.

import com.example.igrejas.models.User;

// dados do registo quando a API devolve utilizador e token
public class SignupData {
        // campos que guardam os dados deste objeto
        private User user;
        private String jwt;

        // método para devolver um valor guardado
        public User getUser() {
            return user;
        }

        public String getJwt() {
            return jwt;
        }
    }


