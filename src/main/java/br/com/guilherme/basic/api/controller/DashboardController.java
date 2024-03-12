package br.com.guilherme.basic.api.controller;

import br.com.guilherme.basic.api.service.DashboardService;
import br.com.guilherme.basic.model.output.DashboardOutput;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dashboard")
public class DashboardController {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private final DashboardService dashboardService;


    @GetMapping
    public ResponseEntity<DashboardOutput> inscricaoAll() {
        DashboardOutput dashboardOutput = new DashboardOutput();
        dashboardOutput.setQuantidadeUserByMesOutput(dashboardService.quantidadeUsuariosByMes());
        dashboardOutput.setQuantidadeAllOutput(dashboardService.quantidadeAll());
        return ResponseEntity.ok(dashboardOutput);
    }


}
