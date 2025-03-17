package com.dex.moneyapi.moneyapi.events;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationEvent;

public class RecursoCriadoEvent extends ApplicationEvent {

    private static final long serialVersionUID = 1L;

    private HttpServletResponse resp;

    private Long codigo;


    public RecursoCriadoEvent(Object source, HttpServletResponse response, Long codigo) {
        super(source);
        this.resp = response;
        this.codigo = codigo;

    }
    public HttpServletResponse getResp() {
        return resp;
    }
    public Long getCodigo() {
        return codigo;
    }
}
