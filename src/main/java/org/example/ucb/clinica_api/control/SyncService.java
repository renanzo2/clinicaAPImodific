package org.example.ucb.clinica_api.control;

import org.example.ucb.clinica_api.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SyncService {

    @Autowired
    private RepositorioDeAnimal repoAnimalSql;

    @Autowired
    private RepositorioDeFichaAnimal repoFichaMongo;

    public void syncMySqlToMongo() {
        System.out.println("======================");
        System.out.println("Iiniciando conexão MySQL -> MongoDB...");

        repoFichaMongo.deleteAll();
        System.out.println("Coleção 'fichas_animais' limpa");


        List<Animal> todosAnimaisSql = repoAnimalSql.findAll();
        System.out.println("->" + todosAnimaisSql.size() + " animais encontrados no MySQL");

        for( Animal animalSql : todosAnimaisSql) {
            FichaAnimal fichaMongo = new FichaAnimal();
            fichaMongo.setRfid(animalSql.getRfid());
            fichaMongo.setNomeAnimal(animalSql.getNome());
            fichaMongo.setEspecie(animalSql.getEspecie());
            fichaMongo.setPorte(animalSql.getPorte());
            fichaMongo.setIdade(animalSql.getIdade());

            Dono donoSql = animalSql.getDono();
            DonoEmbed donoEmbed = new DonoEmbed();
            if (donoSql != null) {
                donoEmbed.setCpf(donoSql.getCpf());
                donoEmbed.setNomeDono(donoSql.getNome());
                donoEmbed.setTelefone(donoSql.getTelefone());
            }
            fichaMongo.setDono(donoEmbed);

            List<HistoricoConsulta> historicoMongo = new ArrayList<>();

            for (Consulta consultaSql : animalSql.getConsultas()) {
                HistoricoConsulta histConsulta = new HistoricoConsulta();

                histConsulta.setConsultaId(consultaSql.getId());
                histConsulta.setDiagnostico(consultaSql.getDiagnostico());
                histConsulta.setDataConsulta(consultaSql.getDataConsulta());
                histConsulta.setHoraConsulta(consultaSql.getHoraConsulta());
                histConsulta.setTipo(consultaSql.getTipo());

                if (consultaSql.getVeterinario() != null) {
                    histConsulta.setNomeVeterinario(consultaSql.getVeterinario().getNome());
                }

                if (consultaSql.getTratamentos() != null &&  !consultaSql.getTratamentos().isEmpty()) {
                    Tratamento tratamentoSql = consultaSql.getTratamentos().get(0);

                    TratamentoEmbed tratamentoEmbed = new TratamentoEmbed();
                    tratamentoEmbed.setTratamentoId(tratamentoSql.getId());
                    tratamentoEmbed.setDescricao(tratamentoSql.getDescricao());
                    tratamentoEmbed.setAntibiotico(tratamentoSql.isAntibiotico());

                    histConsulta.setTratamento(tratamentoEmbed);
                }

                historicoMongo.add(histConsulta);
            }
            fichaMongo.setHistoricoConsultas(historicoMongo);
            repoFichaMongo.save(fichaMongo);
        }
        System.out.println("Conexão concluída! " + todosAnimaisSql.size() + " documentos salvos no MongoDB");
        System.out.println("====================");
    }

    public void sincronizarAnimalEspecifico(String rfid) {

        //Busca todos os dados do animal no MySQL
        Animal animalsql = repoAnimalSql.findById(rfid).orElse(null);

        if(animalsql != null) {
            //Cria/Atualiza o objeto do MongoDB
            FichaAnimal fichaMongo = new FichaAnimal();
            fichaMongo.setRfid(animalsql.getRfid());
            fichaMongo.setNomeAnimal(animalsql.getNome());
            fichaMongo.setEspecie(animalsql.getEspecie());
            fichaMongo.setPorte(animalsql.getPorte());
            fichaMongo.setIdade(animalsql.getIdade());

            Dono donoSql = animalsql.getDono();
            DonoEmbed donoEmbed = new DonoEmbed();
            if (donoSql != null) {
                donoEmbed.setCpf(donoSql.getCpf());
                donoEmbed.setNomeDono(donoSql.getNome());
                donoEmbed.setTelefone(donoSql.getTelefone());
            }
            fichaMongo.setDono(donoEmbed);
            //Inicializa lista vazia (animal sem histórico)
            //caso já exista mapeia
            List<HistoricoConsulta> historicoMongo = new ArrayList<>();
            if (animalsql.getConsultas() != null){
                for (Consulta consultaSql : animalsql.getConsultas()) {
                    HistoricoConsulta histconsulta = new HistoricoConsulta();
                    histconsulta.setConsultaId(consultaSql.getId());
                    histconsulta.setDiagnostico(consultaSql.getDiagnostico());
                    histconsulta.setDataConsulta(consultaSql.getDataConsulta());
                    histconsulta.setHoraConsulta(consultaSql.getHoraConsulta());
                    histconsulta.setTipo(consultaSql.getTipo());

                    if (consultaSql.getVeterinario() != null) {
                        histconsulta.setNomeVeterinario(consultaSql.getVeterinario().getNome());
                    }
                    if (consultaSql.getTratamentos() != null && !consultaSql.getTratamentos().isEmpty()) {
                        Tratamento tramanetoSql = consultaSql.getTratamentos().get(0);
                        TratamentoEmbed tratamentoEmbed = new TratamentoEmbed();
                        tratamentoEmbed.setTratamentoId(tramanetoSql.getId());
                        tratamentoEmbed.setDescricao(tramanetoSql.getDescricao());
                        tratamentoEmbed.setAntibiotico(tramanetoSql.isAntibiotico());
                        histconsulta.setTratamento(tratamentoEmbed);
                    }
                    historicoMongo.add(histconsulta);
                }
            }fichaMongo.setHistoricoConsultas(historicoMongo);

            //Salva no mongoDB
            repoFichaMongo.save(fichaMongo);
            System.out.println("Sincronização automática realizada para o aniaml: " + rfid);
        }
    }

    public void removerAnimalMongo(String rfid){
        if (repoFichaMongo.existsById(rfid)) {
            repoFichaMongo.deleteById(rfid);
            System.out.println("Animal removido do MongoDB (" + rfid + ")");
        }
    }
}
