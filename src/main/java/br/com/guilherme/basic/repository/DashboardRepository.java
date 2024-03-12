package br.com.guilherme.basic.repository;

import br.com.guilherme.basic.model.output.QuantidadeAllOutput;
import br.com.guilherme.basic.model.output.QuantidadeUserByMesOutput;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class DashboardRepository  {
    @PersistenceContext
    private EntityManager entityManager;

    public List<QuantidadeUserByMesOutput> getQuantidadeUsuariosByMes() {
        String sql = "SELECT " +
                "    TO_CHAR(created, 'MM/YYYY') AS mes_ano, " +
                "    CAST(COUNT(*) AS INTEGER)  AS qtd " +
                "FROM " +
                "    public.users " +
                "WHERE " +
                "     actived = true " +
                "GROUP BY " +
                "    TO_CHAR(created, 'MM/YYYY') " +
                "ORDER BY " +
                "    mes_ano ";

        return (List<QuantidadeUserByMesOutput>) entityManager.createNativeQuery(sql)
                .unwrap(NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(QuantidadeUserByMesOutput.class))
                .getResultList();
    }

    public QuantidadeAllOutput getQuantidadeAll() {
        String sql = "SELECT " +
                "  (SELECT CAST(COUNT(*) AS INTEGER) FROM public.users WHERE actived = true) AS qtd_usuarios ";

        return (QuantidadeAllOutput) entityManager.createNativeQuery(sql)
                .unwrap(NativeQuery.class)
                .setResultTransformer(Transformers.aliasToBean(QuantidadeAllOutput.class))
                .getSingleResult();
    }

}
