package com.aga.DelinquentApplication.logic;

import com.aga.DelinquentApplication.DelinquentApplication;
import com.aga.DelinquentApplication.models.Case;
import com.aga.DelinquentApplication.models.Delinquent;
import com.aga.DelinquentApplication.services.caso.CaseService;
import com.aga.DelinquentApplication.services.delinquent.DelinquentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Service
public class Logic {

    @Autowired
    private DelinquentService delinquentService;
    @Autowired
    CaseService caseService;

    private static final Logger logger = LoggerFactory.getLogger(DelinquentApplication.class);

    private String nl = System.lineSeparator();

    private void showMenu(){
        logger.info("""
                *** Gestión de delincuentes y casos ***
                -- Tema delincuentes --
                1. Listar delincuentes
                2. Buscar delincuente
                3. Agregar delincuente
                -- Tema casos --
                4. Nuevo caso
                5. Buscar caso
                6. Listar casos
                7. Añadir delincuente a un caso
                8. Cerrar caso
                9. Salir
                Elige un opcion:""");
    }

    public void menu() {
        boolean exit = false;
        Scanner console = new Scanner(System.in);
        while (!exit) {
            showMenu();
            exit = options(console);
            logger.info(nl);

        }
    }

    private void wait(Logger logger,Scanner console){
        logger.info("Pulse enter tecla para salir..."+nl);
        console.nextLine();
    }

    private boolean options(Scanner console) {
        int opcion = Integer.parseInt(console.nextLine());
        boolean salir = false;
        switch (opcion){
            case 1 -> { // Listar delincuentes
                listDelinquents(console);
            }
            case 2 -> { // Buscar delincuente por id
                listDelinquent(console);
            }
            case 3 -> { // Agregar delincuente
                addDelinquent(console);
            }
            case 4 -> {
                addCase(console);
            }
            case 5 -> {
                getCase(console);
            }
            case 6 -> {
                listCases(console);
            }
            case 7 -> {
                addDelinquentCase(console);
            }
            case 8 -> {
                updateCaseClosed(console);
            }
            case 9 -> { //Salir
                logger.info("Hasta pronto!" + nl + nl);
                salir = true;
            }
            default -> logger.info("Opcion NO reconocida: " + opcion + nl);
        }// fin switch
        return salir;
    }

    private void listDelinquents(Scanner console) {
        List<Delinquent> delinquents = delinquentService.listDelinquent();
        delinquents.forEach((delinquent -> logger.info(delinquent.toString() + nl)));
        wait(logger, console);
    }

    private void listCases(Scanner console) {
        List<Case> cases = caseService.listCase();
        cases.forEach((x -> logger.info(x.toString() + nl)));
        wait(logger, console);
    }

    private void addDelinquent(Scanner console) {
        logger.info("Agregar delincuente: " + nl);
        logger.info("Nombre: ");
        var nombre = console.nextLine();
        logger.info("Apellido: ");
        var apellidos = console.nextLine();
        logger.info("Alias: ");
        var alias = console.nextLine();
        logger.info("Edad: ");
        var edad = console.nextLine();
        logger.info("Genero: ");
        var genero = console.nextLine();
        logger.info("Nacionalidad: ");
        var nacionalidad = console.nextLine();
        logger.info("Description: ");
        var description = console.nextLine();
        logger.info("Pronvincia: ");
        var province = console.nextLine();
        logger.info("Localidad: ");
        var locality = console.nextLine();

        Date date = new Date();

        Delinquent delinquent = new Delinquent(nombre,apellidos,alias,Integer.parseInt(edad),genero,nacionalidad,date,description,province,locality);
        delinquentService.save(delinquent);
        logger.info("Delincuente agregado: " + delinquent + nl);
        wait(logger, console);
    }

    private void listDelinquent(Scanner console) {
        logger.info("Introduce el identificador a buscar: ");
        int idDelinquent = Integer.parseInt(console.nextLine());
        Delinquent delinquent = delinquentService.getDelinquent(idDelinquent);
        if(delinquent != null){
            logger.info("Delincuente encontrado: " + nl);
            logger.info(delinquent + nl);
            logger.info("Casos en lo que esta metido: --total de casos: "+ delinquent.cases.size() +nl);
            for (Case x:delinquent.cases){
                logger.info(x + nl);
            }
        }
        else{
            logger.info("Delincuente No encontrado con id: " + idDelinquent + nl);
        }
        wait(logger, console);
    }

    private void getCase(Scanner console) {
        logger.info("Introduce el identificador a buscar: ");
        int idCase = Integer.parseInt(console.nextLine());
        Case c = caseService.getCase(idCase);
        if(c != null){
            logger.info("Caso encontrado: " + nl);
            logger.info(c + nl);
            logger.info("Delincuentes implicados : --total: "+ c.getDelinquents().size() +nl);
            for (Delinquent x: c.getDelinquents()){
                logger.info(x + nl);
            }
        }
        else{
            logger.info("Caso No encontrado con id: " + idCase + nl);
        }
        wait(logger, console);
    }

    private void addCase(Scanner console) {
        logger.info("Agregar Caso: " + nl);
        logger.info("Nombre: ");
        String nombre = console.nextLine();
        logger.info("Descripcion: ");
        String descripcion = console.nextLine();
        logger.info("Tipo: ");
        String tipo = console.nextLine();
        logger.info("Localización: ");
        String Localizacion = console.nextLine();
        Date date = new Date();
        Case c = new Case(nombre,descripcion,tipo,"Open",date,Localizacion);
        caseService.save(c);
        logger.info("Caso agregado: " + c + nl);
        wait(logger, console);
    }

    private void addDelinquentCase(Scanner console) {

        boolean change = true;
        int idCase = 0;
        while (change) {
            logger.info("Introduce id del caso: ");
            idCase = Integer.parseInt(console.nextLine());
            Case c = caseService.getCase(idCase);
            if (c != null) {
                change = false;
            }
        }
        change = true;
        int idDelinquent = 0;
        while (change) {
            logger.info("Introduce id del delincuente: ");
            idDelinquent = Integer.parseInt(console.nextLine());
            Delinquent delinquent = delinquentService.getDelinquent(idDelinquent);
            if (delinquent != null) {
                change = false;
            }
        }
        Case c = caseService.getCase(idCase);
        Delinquent d = new Delinquent(idDelinquent);
        c.getDelinquents().add(d);
        caseService.save(c);
        logger.info("Delincuente añadido al caso."+nl);
        wait(logger, console);
    }

    private void updateCaseClosed(Scanner console) {
        boolean change = true;
        int idCase = 0;
        while (change) {
            logger.info("Introduce id del caso: ");
            idCase = Integer.parseInt(console.nextLine());
            Case c = caseService.getCase(idCase);
            if (c != null) {
                change = false;
            }
        }

        Date date = new Date();

        caseService.updateClosedCase(idCase,date);
        logger.info("Caso cerrado."+nl);
        wait(logger, console);
    }
}
