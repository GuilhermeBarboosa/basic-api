package br.com.guilherme.basic.api.service;

import br.com.guilherme.basic.model.output.QuantidadeAllOutput;
import br.com.guilherme.basic.model.output.QuantidadeUserByMesOutput;
import br.com.guilherme.basic.repository.DashboardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {
    @Autowired
    private final DashboardRepository dashboardRepository;



    public List<QuantidadeUserByMesOutput> quantidadeUsuariosByMes() {
        return dashboardRepository.getQuantidadeUsuariosByMes();
    }

    public QuantidadeAllOutput quantidadeAll() {
        return dashboardRepository.getQuantidadeAll();
    }

}
