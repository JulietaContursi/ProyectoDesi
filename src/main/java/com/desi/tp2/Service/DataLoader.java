package com.desi.tp2.Service;
import com.desi.tp2.Model.ModelAvion;
import com.desi.tp2.Model.ModelCP;
import com.desi.tp2.Model.ModelCiudad;
import com.desi.tp2.Model.ModelCliente;
import com.desi.tp2.Repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final RepoAvion RepoAvion;
    private final RepoCiudad RepoCiudad;
    private final RepoCliente RepoCliente;
    private final RepoCP RepoCP;


    public DataLoader(RepoAvion RepoAvion, RepoCiudad RepoCiudad, RepoCliente RepoCliente, RepoCP RepoCP) {
        this.RepoAvion = RepoAvion;
        this.RepoCiudad = RepoCiudad;
        this.RepoCliente = RepoCliente;
        this.RepoCP = RepoCP;
    }
    @Override
    public void run(String... args) {
        // cargamos dos registros en la tabla de aviones
        if(RepoAvion.count()==0){
            RepoAvion.save(new ModelAvion("McFly 2020", 20, 4,"FlyBondy"));
            RepoAvion.save(new ModelAvion("Fl3030", 24,6, "Aerolineas Argentinas"));
            RepoAvion.save(new ModelAvion("RTVB0054", 22,6, "Luftansa"));
            RepoAvion.save(new ModelAvion("Airbus A380", 24,8, "Aerolíneas Argentinas"));
            RepoAvion.save(new ModelAvion("Boeing 737-800", 24,6, "Aerolíneas Argentinas"));
            RepoAvion.save(new ModelAvion("Boeing 737 MAX 8", 26,8, "Aerolíneas Argentinas"));
        }
        if(RepoCiudad.count()==0){
            RepoCiudad.save(new ModelCiudad("Buenos Aires",1000));
            RepoCiudad.save(new ModelCiudad("Córdoba, Argentina",5000));
            RepoCiudad.save(new ModelCiudad("Mendoza, Argentina",5500));
            RepoCiudad.save(new ModelCiudad("Neuquén, Argentina",8300));
            RepoCiudad.save(new ModelCiudad("Rosario, Argentina",2000));
            RepoCiudad.save(new ModelCiudad("Salta, Argentina",4400));
            RepoCiudad.save(new ModelCiudad("New York. USA",10002));
            RepoCiudad.save(new ModelCiudad("Amsterdam, Netherlands",1101));
            RepoCiudad.save(new ModelCiudad("Berlin, Germany",10115));
        }
        if(RepoCP.count()==0){
            RepoCP.save(new ModelCP("Tasa única",1050.00,21.00,4,2500,950));
        }
        if(RepoCliente.count()==0){
            RepoCliente.save(new ModelCliente("Medina Mario",20256698,"Las Heras 2020","medinamario@gmail.com", LocalDate.parse("1990-01-01"),123456));
            RepoCliente.save(new ModelCliente("Pedroni Esteban",24887978,"Fcdo. Zuviría 5050","estebanpedroni@gmail.com", LocalDate.parse("1975-10-15"),1245587));
            RepoCliente.save(new ModelCliente("Rodriguez María",23887978,"Brasil 5647 1 Dpto A","mariarodriguez@gmail.com", LocalDate.parse("1972-04-22"),1112500));

        }
        
    }
}
