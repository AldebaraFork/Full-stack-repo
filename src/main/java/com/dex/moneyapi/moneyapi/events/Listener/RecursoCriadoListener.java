package com.dex.moneyapi.moneyapi.events.Listener;

import com.dex.moneyapi.moneyapi.events.RecursoCriadoEvent;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Component
public class RecursoCriadoListener implements ApplicationListener<RecursoCriadoEvent> {

    @Override
    public void onApplicationEvent(RecursoCriadoEvent recursoCriadoEvent) {
        HttpServletResponse response = recursoCriadoEvent.getResp();
        Long codigo = recursoCriadoEvent.getCodigo();


        AdicionarHeaderLocation(codigo, response);
    }

    private static void AdicionarHeaderLocation(Long codigo, HttpServletResponse response) {
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(codigo).toUri();
        response.setHeader("Location", uri.toASCIIString());
    }
}
