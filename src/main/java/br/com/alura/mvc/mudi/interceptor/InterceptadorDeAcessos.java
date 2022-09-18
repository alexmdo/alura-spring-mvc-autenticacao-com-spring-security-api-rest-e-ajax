package br.com.alura.mvc.mudi.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Array;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InterceptadorDeAcessos extends HandlerInterceptorAdapter {

    public static final List<Acesso> acessos = new ArrayList<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Acesso acesso = new Acesso(request.getRequestURI(), LocalDateTime.now());
        request.setAttribute("acesso", acesso);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Acesso acesso = (Acesso) request.getAttribute("acesso");
        acesso.setDuracao(Duration.between(acesso.getData(), LocalDateTime.now()));
        acessos.add(acesso);
    }

    public class Acesso {
        private final String path;
        private final LocalDateTime data;
        private Duration duracao;

        public Acesso(String path, LocalDateTime data) {
            this.path = path;
            this.data = data;
        }

        public String getPath() {
            return path;
        }

        public LocalDateTime getData() {
            return data;
        }

        public void setDuracao(Duration duracao) {
            this.duracao = duracao;
        }

        public Duration getDuracao() {
            return duracao;
        }
    }
}
